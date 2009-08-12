package ru.sgnhp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.Task;
import ru.sgnhp.domain.Workflow;
import ru.sgnhp.domain.WorkflowUser;
import ru.sgnhp.service.ITaskManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class RegisterTaskFormController extends SimpleFormController {

    private ITaskManagerService taskManagerService;
    private IWorkflowManagerService workflowManagerService;

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        Task task = (Task) command;
        String files = request.getParameter("hasFiles");
        if (files != null) {
            request.getSession().setAttribute("task", task);
            this.setSuccessView("upload.htm");
        } else {
            task = taskManagerService.saveTask(task);
            WorkflowUser initiator = (WorkflowUser) request.getSession().getAttribute("initiator");
            String[] userUids = (String[]) request.getSession().getAttribute("checks");
            for (String uid : userUids) {
                Workflow wf = new Workflow();
                wf.setTaskUid(task.getUid());
                wf.setParentUserUid(initiator.getUid());
                wf.setUserUid(Long.valueOf(uid));
                wf.setDescription(task.getDescription());
                wf.setState(Short.valueOf("0"));
                wf.setAssignDate(task.getStartDate());
                workflowManagerService.assignTaskToUser(wf);
            }
            this.setSuccessView("index.htm");
        }
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    /*@Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
    String dateFormat = getMessageSourceAccessor().getMessage("format.date","dd.MM.yyyy");
    SimpleDateFormat df = new SimpleDateFormat(dateFormat);
    df.setLenient(true);
    binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(df, true));
    }*/
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        request.setAttribute("actionUrl", "registerTask.htm");
        Task task = new Task();
        task.setInternalNumber(taskManagerService.getTaskNewNumber());
        task.setExternalNumber("б/н");
        task.setStartDate(DateUtils.nowString());
        task.setDueDate(DateUtils.increaseDateString(3));
        return task;
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    /**
     * @return the workflowManagerService
     */
    public IWorkflowManagerService getWorkflowManagerService() {
        return workflowManagerService;
    }

    /**
     * @param workflowManagerService the workflowManagerService to set
     */
    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
