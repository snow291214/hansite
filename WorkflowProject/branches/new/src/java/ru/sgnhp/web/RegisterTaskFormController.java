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
import ru.sgnhp.service.IStateManagerService;
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
    private IStateManagerService stateManagerService;

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        TaskBean task = (TaskBean) command;
        String files = request.getParameter("hasFiles");
        if (files != null) {
            request.getSession().setAttribute("task", task);
            this.setSuccessView("upload.htm");
        } else {
            task = taskManagerService.save(task);
            WorkflowUserBean initiator = (WorkflowUserBean) request.getSession().getAttribute("initiator");
            String[] userUids = (String[]) request.getSession().getAttribute("checks");

            //Refactoring is needed here.
            for (String uid : userUids) {
                WorkflowBean wf = new WorkflowBean();
                wf.setParentUid(-1L);
                wf.setTaskBean(task);
                wf.setAssignee(initiator);
                wf.setReceiver(userManagerService.get(Long.valueOf(uid)));
                wf.setDescription(task.getDescription());
                wf.setState(stateManagerService.get(0L));
                wf.setAssignDate(task.getStartDate());
                workflowManagerService.assignTaskToUser(wf);
            }
            request.getSession().setAttribute("task", null);
            request.getSession().setAttribute("checks", null);
            this.setSuccessView("index.htm");
        }
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        request.setAttribute("actionUrl", "registerTask.htm");
        TaskBean task = (TaskBean) request.getAttribute("task");
        if (task == null) {
            task = new TaskBean();
            task.setInternalNumber(taskManagerService.getNewInternalNumber());
            task.setExternalNumber("б/н");
            task.setStartDate(DateUtils.nowDate());
            task.setDueDate(DateUtils.increaseDate(DateUtils.nowDate(),3));
        }
        return task;
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

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
