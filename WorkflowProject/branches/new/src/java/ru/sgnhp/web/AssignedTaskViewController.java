package ru.sgnhp.web;

import java.io.IOException;
import java.util.ArrayList;
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
 * @author alexey
 */
public class AssignedTaskViewController implements Controller {

    //private IUserManagerService userManagerService;
    private IWorkflowManagerService workflowManagerService;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long initiatorUid = ((WorkflowUserBean) request.getSession().getAttribute("initiator")).getUid();
        List<WorkflowBean> wfs = null;
        int count = 0;
        if (request.getParameter("completed") == null) {
            wfs = workflowManagerService.getAssignedWorkflowsByUserUid(initiatorUid, false);
        } else {
            wfs = workflowManagerService.getAssignedWorkflowsByUserUid(initiatorUid, true);
        }
        if (wfs != null){
            count = wfs.size();
        }
        request.setAttribute("count", count);
        return new ModelAndView("assignedTask", "assigned", wfs);
    }

    /*public void setUserManagerService(IUserManagerService userManagerService) {
    this.userManagerService = userManagerService;
    }*/
    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
