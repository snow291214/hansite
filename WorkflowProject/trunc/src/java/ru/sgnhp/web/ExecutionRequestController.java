package ru.sgnhp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.service.IWorkflowManagerService;


public class ExecutionRequestController  implements Controller {

    private IWorkflowManagerService workflowManagerService;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String workflowUid = request.getParameter("worflowID");
        WorkflowBean workflowBean = workflowManagerService.getWorkflowByUid(Long.parseLong(workflowUid));
        workflowManagerService.sendmailRemind(workflowBean);
        return null;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

}
