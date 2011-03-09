package ru.sgnhp.web.forms;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.WorkflowBeanDto;
import ru.sgnhp.service.IMailService;
import ru.sgnhp.service.IStateManagerService;
import ru.sgnhp.service.IUserManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class ApproveAndAssignWorkflowController extends AbstractWizardFormController {

    private IWorkflowManagerService workflowManagerService;
    private IStateManagerService stateManagerService;
    private IUserManagerService userManagerService;
    private IMailService mailService;
    private String serverName;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        List<WorkflowUserBean> users = getUserManagerService().getAll();
        String workflowUid = request.getParameter("workflowID");
        request.setAttribute("users", users);
        WorkflowBeanDto workflowBeanDto = new WorkflowBeanDto();
        workflowBeanDto.setUid(Long.parseLong(workflowUid));
        workflowBeanDto.setDescription("");
        return workflowBeanDto;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getParameter("checks") != null) {
            request.getSession().setAttribute("checks", request.getParameterValues("checks"));
        }
        return super.handleRequest(request, response);
    }


    @Override
    protected ModelAndView processCancel(HttpServletRequest req,
            HttpServletResponse resp, Object command,
            BindException errors) throws Exception {
        return new ModelAndView(new RedirectView("index.htm"));
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException be) throws Exception {
        WorkflowBeanDto workflowBeanDto = (WorkflowBeanDto)command;
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        WorkflowUserBean initiator = getUserManagerService().getUserByLogin(currentUser);

        WorkflowBean workflowBean = workflowManagerService.getWorkflowByUid(workflowBeanDto.getUid());

        workflowBean.setState(getStateManagerService().get(3L));
        workflowBean.setWorkflowNote(workflowBean.getWorkflowNote()
                + ". Выполнение подтверждаю. <br />"
                + initiator.getLastName() + " " + initiator.getFirstName() + " "
                + initiator.getMiddleName());
        workflowManagerService.save(workflowBean);
        getMailService().sendmailChangeState(workflowBean);

        String[] userUids = (String[]) request.getSession().getAttribute("checks");
        for (String uid : userUids) {
            WorkflowBean _workflowBean = new WorkflowBean();
            _workflowBean.setParentUid(workflowBean.getUid());
            _workflowBean.setAssignee(initiator);
            _workflowBean.setReceiver(userManagerService.get(Long.valueOf(uid)));
            _workflowBean.setDescription(workflowBeanDto.getDescription());
            _workflowBean.setTaskBean(workflowBean.getTaskBean());
            _workflowBean.setAssignDate(DateUtils.nowDate());
            _workflowBean.setState(stateManagerService.get(0L));
            _workflowBean.setWorkflowNote("");
            workflowManagerService.assignTaskToUser(_workflowBean);
        }
        request.getSession().setAttribute("checks", null);

        return new ModelAndView(new RedirectView("index.htm"));
    }

    public IWorkflowManagerService getWorkflowManagerService() {
        return workflowManagerService;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    public IStateManagerService getStateManagerService() {
        return stateManagerService;
    }

    public void setStateManagerService(IStateManagerService stateManagerService) {
        this.stateManagerService = stateManagerService;
    }

    public IUserManagerService getUserManagerService() {
        return userManagerService;
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    public IMailService getMailService() {
        return mailService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
