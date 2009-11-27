package ru.sgnhp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.TaskBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.ITaskManagerService;
import ru.sgnhp.service.IUserManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 * Отличие от CreateAssignWorkflowFormController в  том, что здесь первичный
 * ввод задачи.
 * Заполняются атрибуты, потом сохраняется задача, потом создается воркфлоу.
 * CreateAssignWorkflowFormController делает то же самое, только можно указать
 * резолюцию.
 * 
 *****
 */
public class RegisterTaskFormController extends SimpleFormController {

    private ITaskManagerService taskManagerService;
    private IWorkflowManagerService workflowManagerService;
    private IUserManagerService userManagerService;

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        TaskBean task = (TaskBean) command;
        String files = request.getParameter("hasFiles");
        if (files != null) {
            request.getSession().setAttribute("task", task);
            this.setSuccessView("upload.htm");
        } else {
            task = taskManagerService.saveTask(task);
            WorkflowUserBean initiator = (WorkflowUserBean) request.getSession().getAttribute("initiator");
            String[] userUids = (String[]) request.getSession().getAttribute("checks");

            //Refactoring is needed here.
            for (String uid : userUids) {
                WorkflowBean wf = new WorkflowBean();
                wf.setParentUid(Long.parseLong("-1"));
                wf.setTaskUid(task.getUid());
                wf.setParentUserUid(initiator.getUid());
                wf.setUserUid(Long.valueOf(uid));
                wf.setDescription(task.getDescription());
                wf.setState("0");
                wf.setAssignDate(task.getStartDate());
                wf.setTask(task);
                wf.setAssignee(userManagerService.getUserByUid(wf.getParentUserUid()));
                wf.setReceiver(userManagerService.getUserByUid(wf.getUserUid()));
                workflowManagerService.assignTaskToUser(wf);
            }
            request.getSession().setAttribute("task", null);
            request.getSession().setAttribute("checks", null);
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
        TaskBean task = (TaskBean) request.getAttribute("task");
        if (task == null) {
            task = new TaskBean();
            task.setInternalNumber(taskManagerService.getTaskNewNumber());
            task.setExternalNumber("б/н");
            task.setStartDate(DateUtils.nowString("dd.MM.yyyy"));
            task.setDueDate(DateUtils.increaseDateString(3));
        }
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

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
