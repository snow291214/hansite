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
import org.springframework.security.context.SecurityContextHolder;
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
import ru.sgnhp.domain.TaskBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.DocumentDto;
import ru.sgnhp.service.IDocumentFileService;
import ru.sgnhp.service.IDocumentService;
import ru.sgnhp.service.IDocumentTypeService;
import ru.sgnhp.service.IStateManagerService;
import ru.sgnhp.service.ITaskManagerService;
import ru.sgnhp.service.IUserManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

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
    //private IMailService mailService;
    private ITaskManagerService taskManagerService;
    private IWorkflowManagerService workflowManagerService;
    private IStateManagerService stateManagerService;
    private int currentPage;
    private String serverName;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        List<WorkflowUserBean> users = getUserManagerService().getAll();
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

//    @Override
//    protected int getTargetPage(HttpServletRequest request, int arg1) {
//        currentPage = super.getTargetPage(request, arg1);
//        return super.getTargetPage(request, arg1);
//    }
    @Override
    protected int getCurrentPage(HttpServletRequest request) {
        currentPage = super.getCurrentPage(request) + 1;
        return super.getCurrentPage(request);
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        int currentPage = this.getCurrentPage(request);
        switch (currentPage) {
            case 1:
                request.setAttribute("users", getUserManagerService().getAll());
                break;
        }
        if (request.getParameter("contactPerson") != null) {
            request.getSession().setAttribute("contactPersonUid", request.getParameter("contactPerson"));
            request.getSession().setAttribute("controlPersonUid", request.getParameter("controlPerson"));
        }
        if (request.getParameter("checks") != null) {
            request.getSession().setAttribute("checks", request.getParameterValues("checks"));
        }
        return super.handleRequest(request, response);
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException be) throws Exception {
        DocumentDto documentDto = (DocumentDto) command;
        String responibleUid = (String) request.getSession().getAttribute("contactPersonUid");
        String controlPersonUid = (String) request.getSession().getAttribute("controlPersonUid");
        WorkflowUserBean contactUserBean = getUserManagerService().get(Long.parseLong(responibleUid));
        WorkflowUserBean controlUserBean = getUserManagerService().get(Long.parseLong(controlPersonUid));
        String[] userUids = (String[]) request.getSession().getAttribute("checks");

        /*Сохранение распорядительного документа в БД*/
        DocumentBean documentBean = new DocumentBean();
        documentBean.setDescription(documentDto.getDescription());
        documentBean.setDocumentDate(documentDto.getDocumentDate());
        documentBean.setDocumentNumber(documentDto.getIncomingNumber());
        documentBean.setDocumentTypeBean(getDocumentTypeService().get(documentDto.getDocumentTypeUid()));
        documentBean.setContactPerson(contactUserBean);
        documentBean.setControlPerson(controlUserBean);
        documentBean = getDocumentService().save(documentBean);

        /*Сохранение файлов в БД*/
        final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        final Map files = multiRequest.getFileMap();
        for (Object file : files.values()) {
            DocumentFileBean documentFileBean = new DocumentFileBean();
            documentFileBean.setBlobField(((MultipartFile) file).getBytes());
            documentFileBean.setFileName(((MultipartFile) file).getOriginalFilename());
            documentFileBean.setDocumentBean(documentBean);
            getDocumentFileService().save(documentFileBean);
        }
        /*Отправляем письмо*/
        //mailService.sendmailOrder(documentBean);

        /*
        Создание и сохранение задачи ознакомления с распорядительным документом
         */
        TaskBean taskBean = new TaskBean();
        taskBean.setInternalNumber(taskManagerService.getNewInternalNumber());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        taskBean.setDescription(documentBean.getDocumentTypeBean().getDescription()
                + " № " + String.valueOf(documentBean.getDocumentNumber())
                + " от " + df.format(documentBean.getDocumentDate())
                + ". " + documentBean.getDescription());
        taskBean.setStartDate(DateUtils.nowDate());
        taskBean.setDueDate(DateUtils.nowDate());
        taskBean.setExternalAssignee(documentBean.getControlPerson().getLastName()
                + " " + documentBean.getControlPerson().getFirstName()
                + " " + documentBean.getControlPerson().getMiddleName());
        taskBean.setExternalCompany("СГНХП");
        taskBean = getTaskManagerService().save(taskBean);

        /*
        Рассылка пользователям задачи ознакомления с распорядительным документом
         */
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        WorkflowUserBean initiator = getUserManagerService().getUserByLogin(currentUser);
        String description = "Прошу ознакомиться с распорядительным документом. "
                + "<a href=\""+this.getServerName()+"getDocumentFiles.htm?fileID="
                + documentBean.getUid().toString()
                + "\">Ссылка на документ</a>";
        for (String uid : userUids) {
            WorkflowBean wf = new WorkflowBean();
            wf.setParentUid(-1L);
            wf.setTaskBean(taskBean);
            wf.setAssignee(initiator);
            wf.setReceiver(getUserManagerService().get(Long.valueOf(uid)));

            wf.setDescription(description);
            wf.setState(getStateManagerService().get(0L));
            wf.setAssignDate(taskBean.getStartDate());
            getWorkflowManagerService().assignTaskToUser(wf);
        }

        request.getSession().setAttribute("contactPersonUid", null);
        request.getSession().setAttribute("controlPersonUid", null);
        request.getSession().setAttribute("checks", null);
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

    public IStateManagerService getStateManagerService() {
        return stateManagerService;
    }

    public void setStateManagerService(IStateManagerService stateManagerService) {
        this.stateManagerService = stateManagerService;
    }

    public ITaskManagerService getTaskManagerService() {
        return taskManagerService;
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    public IWorkflowManagerService getWorkflowManagerService() {
        return workflowManagerService;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
