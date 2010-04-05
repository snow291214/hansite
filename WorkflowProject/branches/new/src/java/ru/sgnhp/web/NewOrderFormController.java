package ru.sgnhp.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.DocumentBean;
import ru.sgnhp.domain.DocumentFileBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.DocumentDto;
import ru.sgnhp.service.IDocumentFileService;
import ru.sgnhp.service.IDocumentService;
import ru.sgnhp.service.IDocumentTypeService;
import ru.sgnhp.service.IMailService;
import ru.sgnhp.service.IUserManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class NewOrderFormController extends AbstractWizardFormController {

    private IDocumentService documentService;
    private IDocumentTypeService documentTypeService;
    private IDocumentFileService documentFileService;
    private IUserManagerService userManagerService;
    private IMailService mailService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        List<WorkflowUserBean> users = userManagerService.getAll();
        request.setAttribute("users", users);
        DocumentDto documentDto = new DocumentDto();
        documentDto.setDocumentTypeUid(0L);
        documentDto.setDocumentDate(DateUtils.nowDate());
        return documentDto;
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
        super.initBinder(request, binder);
    }

    @Override
    protected ModelAndView processCancel(HttpServletRequest req,
            HttpServletResponse resp, Object command,
            BindException errors) throws Exception {
        return new ModelAndView(new RedirectView("index.htm"));
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getParameter("contactPerson") != null) {
            request.getSession().setAttribute("contactPersonUid", request.getParameter("contactPerson"));
            request.getSession().setAttribute("controlPersonUid", request.getParameter("controlPerson"));
        }
        return super.handleRequest(request, response);
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException be) throws Exception {
        DocumentDto documentDto = (DocumentDto) command;
        String responibleUid = (String) request.getSession().getAttribute("contactPersonUid");
        String controlPersonUid = (String) request.getSession().getAttribute("controlPersonUid");
        WorkflowUserBean contactUserBean = userManagerService.get(Long.parseLong(responibleUid));
        WorkflowUserBean controlUserBean = userManagerService.get(Long.parseLong(controlPersonUid));

        /*Сохранение распорядительного документа в БД*/
        DocumentBean documentBean = new DocumentBean();
        documentBean.setDescription(documentDto.getDescription());
        documentBean.setDocumentDate(documentDto.getDocumentDate());
        documentBean.setDocumentNumber(documentDto.getIncomingNumber());
        documentBean.setDocumentTypeBean(documentTypeService.get(documentDto.getDocumentTypeUid()));
        documentBean.setContactPerson(contactUserBean);
        documentBean.setControlPerson(controlUserBean);
        documentBean = documentService.save(documentBean);

        /*Сохранение файлов в БД*/
        final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        final Map files = multiRequest.getFileMap();
        for (Object file : files.values()) {
            DocumentFileBean documentFileBean = new DocumentFileBean();
            documentFileBean.setBlobField(((MultipartFile) file).getBytes());
            documentFileBean.setFileName(((MultipartFile) file).getOriginalFilename());
            documentFileBean.setDocumentBean(documentBean);
            documentFileService.save(documentFileBean);
        }
        /*Отправляем письмо*/
        mailService.sendmailOrder(documentBean);
        request.getSession().setAttribute("responsibleUid", null);
        return new ModelAndView(new RedirectView("index.htm"));
    }

    public IDocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(IDocumentService documentService) {
        this.documentService = documentService;
    }

    public IDocumentTypeService getDocumentTypeService() {
        return documentTypeService;
    }

    public void setDocumentTypeService(IDocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    public IDocumentFileService getDocumentFileService() {
        return documentFileService;
    }

    public void setDocumentFileService(IDocumentFileService documentFileService) {
        this.documentFileService = documentFileService;
    }

    public IUserManagerService getUserManagerService() {
        return userManagerService;
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    public IMailService getMailService() {
        return mailService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }
}
