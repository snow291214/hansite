package ru.sgnhp.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.SimpleFormController;
import ru.sgnhp.domain.WorkflowBean;
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
        ArrayList<String> path = new ArrayList<String>();
        path.add(workflowBean.getReceiver().getLastName());
        List<String> members = workflowManagerService.getWorkflowMembersByWorkflowUid(workflowBean.getParentUid(), path);
        request.setAttribute("members", members);
        return workflowBean;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
