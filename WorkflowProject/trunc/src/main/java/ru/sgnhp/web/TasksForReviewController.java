package ru.sgnhp.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IUserManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class TasksForReviewController implements Controller {

    private IUserManagerService userManagerService;
    private IWorkflowManagerService workflowManagerService;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        WorkflowUserBean initiator = userManagerService.getUserByLogin(currentUser);
        Long[] stateUids = {5L};
        List<WorkflowBean> workflowBeans = workflowManagerService.getWorkflowsByUserUidAndStateUids(initiator.getUid(), stateUids);
        return new ModelAndView("tasksForReview", "workflowBeans", workflowBeans);
    }

    public IUserManagerService getUserManagerService() {
        return userManagerService;
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
