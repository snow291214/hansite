package ru.sgnhp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IMailService;
import ru.sgnhp.service.IStateManagerService;
import ru.sgnhp.service.IUserManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 * Created on: 17.02.2010
 *
 *****
 */
public class ApproveWorkflowController implements Controller {

    private IWorkflowManagerService workflowManagerService;
    private IStateManagerService stateManagerService;
    private IMailService mailService;
    private IUserManagerService userManagerService;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        WorkflowUserBean initiator = userManagerService.getUserByLogin(currentUser);
        String workflowUid = request.getParameter("workflowID");
        WorkflowBean workflowBean = workflowManagerService.getWorkflowByUid(Long.parseLong(workflowUid));
        workflowBean.setState(stateManagerService.get(3L));
        workflowBean.setWorkflowNote(workflowBean.getWorkflowNote()
                + ". Выполнение подтверждаю. <br />"
                + initiator.getLastName() + " " + initiator.getFirstName() + " "
                + initiator.getMiddleName());
        workflowManagerService.save(workflowBean);
        mailService.sendmailChangeState(workflowBean);
        return new ModelAndView(new RedirectView("tasksForReview.htm"));
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    public void setStateManagerService(IStateManagerService stateManagerService) {
        this.stateManagerService = stateManagerService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
