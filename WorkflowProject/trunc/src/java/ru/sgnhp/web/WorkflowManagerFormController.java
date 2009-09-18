package ru.sgnhp.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.SimpleFormController;
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
public class WorkflowManagerFormController extends SimpleFormController {

    private IWorkflowManagerService workflowManagerService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        request.setAttribute("actionUrl", "workflowManager.htm");
        String workflowUid = request.getParameter("workflowID");
        WorkflowBean workflowBean = workflowManagerService.getWorkflowByUid(Long.parseLong(workflowUid));
        HashMap<Long, ArrayList<WorkflowUserBean>> roadmap = new HashMap<Long, ArrayList<WorkflowUserBean>>();
        if (workflowBean.getParentUid() == -1) {
            ArrayList<WorkflowUserBean> members = new ArrayList<WorkflowUserBean>();
            members.add(workflowBean.getAssignee());
            members.add(workflowBean.getReceiver());
            roadmap.put(workflowBean.getUid(), members);
        } else {
            ArrayList<WorkflowUserBean> members = new ArrayList<WorkflowUserBean>();
            members.add(workflowBean.getAssignee());
            members.add(workflowBean.getReceiver());
            roadmap.put(workflowBean.getUid(), members);
            roadmap = workflowManagerService.getWorkflowMembersByWorkflowUid(workflowBean.getParentUid(), roadmap);
            //roadmap.
        }
        request.setAttribute("roadmap", roadmap);
        return workflowBean;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
