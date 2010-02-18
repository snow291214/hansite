package ru.sgnhp.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class RoadmapController implements Controller {

    private IWorkflowManagerService workflowManagerService;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String workflowUid = request.getParameter("workflowID");
        WorkflowBean workflowBean = workflowManagerService.getWorkflowByUid(Long.parseLong(workflowUid));
        ArrayList<WorkflowBean> roadmap = new ArrayList<WorkflowBean>();
        //if (workflowBean.getParentUid() == -1) {
        //roadmap.add(workflowBean);
        //} else {
        roadmap.add(workflowBean);
        roadmap = this.workflowManagerService.getWorkflowMembersByWorkflowUid(workflowBean.getUid(), workflowBean.getParentUid(), roadmap);
        Collections.reverse(roadmap);
        //}
        return new ModelAndView("roadmap", "roadmap", roadmap);
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
