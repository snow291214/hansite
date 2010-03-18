package ru.sgnhp.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.DocumentDto;
import ru.sgnhp.service.IDocumentFileService;
import ru.sgnhp.service.IDocumentService;
import ru.sgnhp.service.IDocumentTypeService;
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

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
        super.initBinder(request, binder);
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<WorkflowUserBean> users = userManagerService.getAll();
        request.setAttribute("users", users);
        String documentType = request.getParameter("documentType");
        if (documentType != null) {
            ((DocumentDto)this.getCommand(request)).setDocumentTypeUid(Long.parseLong(documentType));
        }
        if (request.getParameter("combobox") != null) {
            request.getSession().setAttribute("userUid", request.getParameter("combobox"));
        }
        return super.handleRequest(request, response);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        DocumentDto documentDto = new DocumentDto();
        documentDto.setDocumentDate(DateUtils.nowDate());
        return documentDto;
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object o, BindException be) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected ModelAndView processCancel(HttpServletRequest req,
            HttpServletResponse resp, Object command,
            BindException errors) throws Exception {
        return new ModelAndView(new RedirectView("index.htm"));
    }

    public void setDocumentService(IDocumentService documentService) {
        this.documentService = documentService;
    }

    public void setDocumentTypeService(IDocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    public void setDocumentFileService(IDocumentFileService documentFileService) {
        this.documentFileService = documentFileService;
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
