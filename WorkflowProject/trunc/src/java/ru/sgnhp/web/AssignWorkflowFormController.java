package ru.sgnhp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.Workflow;
import ru.sgnhp.domain.WorkflowUser;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class AssignWorkflowFormController extends SimpleFormController {

    private IWorkflowManagerService workflowManagerService;

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        Workflow workflow = (Workflow) command;
        WorkflowUser initiator = (WorkflowUser) request.getSession().getAttribute("initiator");
        workflow.setState("1");
        workflowManagerService.updateWorkflow(workflow);
        String[] userUids = (String[]) request.getSession().getAttribute("checks");
        for (String uid : userUids) {
            workflow.setParentUserUid(initiator.getUid());
            workflow.setUserUid(Long.valueOf(uid));
            workflow.setAssignDate(DateUtils.nowString());
            workflow.setState("0");
            workflowManagerService.assignTaskToUser(workflow);
        }
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        String workflowUid = request.getParameter("workflowID");
        Workflow workflow = workflowManagerService.getWorkflowByUid(Long.parseLong(workflowUid));
        workflow.setParentUid(Long.parseLong(workflowUid));
        workflow.setDescription("");
        return workflow;
    }

    public IWorkflowManagerService getWorkflowManagerService() {
        return workflowManagerService;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
