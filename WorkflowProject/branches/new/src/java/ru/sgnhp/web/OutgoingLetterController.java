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
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.OutgoingFileBean;
import ru.sgnhp.domain.OutgoingMailBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.OutgoingMailDto;
import ru.sgnhp.service.IMailService;
import ru.sgnhp.service.IOutgoingFileService;
import ru.sgnhp.service.IOutgoingMailService;
import ru.sgnhp.service.IUserManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class OutgoingLetterController extends AbstractWizardFormController {

    private IUserManagerService userManagerService;
    private IOutgoingMailService outgoingMailService;
    private IOutgoingFileService outgoingFileService;
    private IMailService mailService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getParameter("combobox") != null) {
            request.getSession().setAttribute("responsibleUid", request.getParameter("combobox"));
        }
        return super.handleRequest(request, response);
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
        super.initBinder(request, binder);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        List<WorkflowUserBean> users = userManagerService.getAll();
        request.setAttribute("users", users);
        OutgoingMailDto outgoingMailDto = new OutgoingMailDto();
        outgoingMailDto.setOutgoingNumber(outgoingMailService.getNewOutgoingNumber());
        outgoingMailDto.setOutgoingDate(DateUtils.nowDate());
        outgoingMailDto.setDueDate(DateUtils.increaseDate(DateUtils.nowDate(), 7));
        return outgoingMailDto;
    }

    @Override
    protected ModelAndView processCancel(HttpServletRequest req,
            HttpServletResponse resp, Object command,
            BindException errors) throws Exception {
        return new ModelAndView(new RedirectView("index.htm"));
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors) throws Exception {
        OutgoingMailDto outgoingMailDto = (OutgoingMailDto) command;
        String responibleUid = (String) request.getSession().getAttribute("responsibleUid");
        WorkflowUserBean workflowUserBean = userManagerService.get(Long.parseLong(responibleUid));

//        Сохранение исходящего письма в БД
        OutgoingMailBean outgoingMailBean = new OutgoingMailBean();
        outgoingMailBean.setDescription(outgoingMailDto.getDescription());
        outgoingMailBean.setDocumentumNumber(outgoingMailDto.getDocumentumNumber());
        outgoingMailBean.setDueDate(outgoingMailDto.getDueDate());
        outgoingMailBean.setOutgoingDate(outgoingMailDto.getOutgoingDate());
        outgoingMailBean.setOutgoingNumber(outgoingMailDto.getOutgoingNumber());
        outgoingMailBean.setReceiverCompany(outgoingMailDto.getReceiverCompany());
        outgoingMailBean.setReceiverName(outgoingMailDto.getReceiverName());
        outgoingMailBean.setWorkflowUserBean(workflowUserBean);
        outgoingMailBean.setPrimaveraUid(outgoingMailDto.getPrimaveraUid());
        outgoingMailBean = outgoingMailService.save(outgoingMailBean);

        /* Сохраняем прикрепленные файлы */
        final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        final Map files = multiRequest.getFileMap();
        for (Object file : files.values()) {
            OutgoingFileBean outgoingFileBean = new OutgoingFileBean();
            outgoingFileBean.setOutgoingMailBean(outgoingMailBean);
            outgoingFileBean.setFileName(((MultipartFile) file).getOriginalFilename());
            outgoingFileBean.setBlobField(((MultipartFile) file).getBytes());
            outgoingFileService.save(outgoingFileBean);
        }
        /*Отправляем письмо*/
        mailService.sendmailOutgoing(outgoingMailBean);
        request.getSession().setAttribute("responsibleUid", null);
        return new ModelAndView(new RedirectView("index.htm"));
    }

    @Override
    protected void validatePage(Object command, Errors errors, int page) {
        if (page == 0) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "OutgoingMailDto.description.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiverCompany", "OutgoingMailDto.receiverCompany.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiverName", "OutgoingMailDto.receiverName.empty");
        }
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    public void setOutgoingMailService(IOutgoingMailService outgoingMailService) {
        this.outgoingMailService = outgoingMailService;
    }

    public void setOutgoingFileService(IOutgoingFileService outgoingFileService) {
        this.outgoingFileService = outgoingFileService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }
}
