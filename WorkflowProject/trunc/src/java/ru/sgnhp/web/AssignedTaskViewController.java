/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.domain.Workflow;
import ru.sgnhp.domain.WorkflowUser;
import ru.sgnhp.service.IUserManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/**
 *
 * @author alexey
 */
public class AssignedTaskViewController implements Controller {

    private IUserManagerService userManagerService;
    private IWorkflowManagerService workflowManagerService;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long initiatorUid = ((WorkflowUser) request.getSession().getAttribute("initiator")).getUid();

        List<Workflow> wfs = workflowManagerService.getAssignedWorkflowsByParentUid(initiatorUid);
        for (Workflow wf : wfs) {
            wf.setAssignee(userManagerService.getUserByUid(wf.getParentUserUid()));
            wf.setReceiver(userManagerService.getUserByUid(wf.getUserUid()));
        }
        return new ModelAndView("assignedTask", "assigned", wfs);
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
