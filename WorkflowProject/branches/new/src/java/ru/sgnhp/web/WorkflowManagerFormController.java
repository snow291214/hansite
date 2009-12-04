package ru.sgnhp.web;

import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
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
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        workflowManagerService.updateWorkflow((WorkflowBean) command);
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        request.setAttribute("actionUrl", "workflowManager.htm");
        String workflowUid = request.getParameter("workflowID");
        WorkflowBean workflowBean = getWorkflowManagerService().getWorkflowByUid(Long.parseLong(workflowUid));
        //LinkedHashMap<Long, ArrayList<WorkflowUserBean>> roadmap = new LinkedHashMap<Long, ArrayList<WorkflowUserBean>>();
        ArrayList<WorkflowBean> roadmap = new ArrayList<WorkflowBean>();
        if (workflowBean.getParentUid() == -1) {
            roadmap.add(workflowBean);
        } else {
            roadmap.add(workflowBean);
            roadmap = this.workflowManagerService.getWorkflowMembersByWorkflowUid(workflowBean.getUid(),
                    workflowBean.getParentUid(), roadmap);
            Collections.reverse(roadmap);
        }
        request.setAttribute("roadmap", roadmap);
        request.setAttribute("workflowID", workflowUid);
        return workflowBean;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    public IWorkflowManagerService getWorkflowManagerService() {
        return workflowManagerService;
    }
}
