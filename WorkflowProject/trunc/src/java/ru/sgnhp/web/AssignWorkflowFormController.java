package ru.sgnhp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import ru.sgnhp.domain.Workflow;
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
        return null;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        String workflowUid = request.getParameter("workflowID");
        Workflow workflow = workflowManagerService.getWorkflowByUid(workflowUid);
        return workflow;
    }

    public IWorkflowManagerService getWorkflowManagerService() {
        return workflowManagerService;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
