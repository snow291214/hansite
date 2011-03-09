package ru.sgnhp.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
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
 * @Skype: khudyakov.alexey
 *
 * Отличие от AssignWorkflowFormController в  том, что здесь первичный
 * ввод задачи.
 * Заполняются атрибуты, потом сохраняется задача, потом создается воркфлоу.
 * AssignWorkflowFormController делает то же самое, только можно указать
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
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
        super.initBinder(request, binder);
    }

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        TaskBean task = (TaskBean) command;
        String files = request.getParameter("hasFiles");
        if (files != null) {
            request.getSession().setAttribute("task", task);
            this.setSuccessView("upload.htm");
        } else {
            task = taskManagerService.save(task);
            
            /* Исправление непонятной и премерзкой ошибки */
            final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
            WorkflowUserBean initiator = userManagerService.getUserByLogin(currentUser);

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
            task.setPrimaveraUid("");
            task.setStartDate(DateUtils.nowDate());
            task.setDueDate(DateUtils.increaseDate(DateUtils.nowDate(), 3));
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

    public void setStateManagerService(IStateManagerService stateManagerService) {
        this.stateManagerService = stateManagerService;
    }
}
