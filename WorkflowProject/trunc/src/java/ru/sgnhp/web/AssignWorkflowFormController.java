package ru.sgnhp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
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
        WorkflowBean workflow = (WorkflowBean) command;
        WorkflowUserBean initiator = (WorkflowUserBean) request.getSession().getAttribute("initiator");
        workflow.setState("1");
        workflowManagerService.updateWorkflowState(workflow);
        String[] userUids = (String[]) request.getSession().getAttribute("checks");
        for (String uid : userUids) {
            workflow.setParentUid(workflow.getUid());
            workflow.setParentUserUid(initiator.getUid());
            workflow.setUserUid(Long.valueOf(uid));
            workflow.setAssignDate(DateUtils.nowString("yyyy-MM-dd"));
            workflow.setState("0");
            workflowManagerService.assignTaskToUser(workflow);
        }
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        String workflowUid = request.getParameter("workflowID");
        WorkflowBean workflow = workflowManagerService.getWorkflowByUid(Long.parseLong(workflowUid));
        workflow.setDescription(null);
        return workflow;
    }

    public IWorkflowManagerService getWorkflowManagerService() {
        return workflowManagerService;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
