package ru.sgnhp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.service.IMailService;
import ru.sgnhp.service.IWorkflowManagerService;


public class ExecutionRequestController  implements Controller {

    private IWorkflowManagerService workflowManagerService;
    private IMailService mailService;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String workflowUid = request.getParameter("workflowID");
        WorkflowBean workflowBean = workflowManagerService.getWorkflowByUid(Long.parseLong(workflowUid));
        mailService.sendmailRemind(workflowBean);
        return new ModelAndView(new RedirectView("assignedTask.htm"), "workflowID", workflowUid);
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

}
