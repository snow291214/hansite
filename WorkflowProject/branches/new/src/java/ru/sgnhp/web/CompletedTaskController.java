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
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IWorkflowManagerService;

/**
 *
 * @author 48han
 */
public class CompletedTaskController implements Controller {

    private IWorkflowManagerService workflowManagerService;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long initiatorUid = ((WorkflowUserBean) request.getSession().getAttribute("initiator")).getUid();
        List<WorkflowBean> wfs = workflowManagerService.getCompletedWorkflowsByUserUid(initiatorUid);
        int count = 0;
        if (wfs != null) {
            count = wfs.size();
        }
        request.setAttribute("count", count);
        return new ModelAndView("completedTask", "completed", wfs);
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
