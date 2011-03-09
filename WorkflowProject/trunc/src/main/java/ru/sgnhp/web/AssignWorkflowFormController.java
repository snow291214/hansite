package ru.sgnhp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.WorkflowBeanDto;
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
public class AssignWorkflowFormController extends SimpleFormController {

    private IWorkflowManagerService workflowManagerService;
    private IUserManagerService userManagerService;
    private IStateManagerService stateManagerService;

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        WorkflowBeanDto workflowBeanDto = (WorkflowBeanDto) command;
        
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        WorkflowUserBean initiator = userManagerService.getUserByLogin(currentUser);

        workflowBeanDto = workflowManagerService.updateWorkflowState(workflowBeanDto, stateManagerService.get(1L));
        String[] userUids = (String[]) request.getSession().getAttribute("checks");
        workflowBeanDto.setUserUids(userUids);
        for (String uid : userUids) {
            WorkflowBean workflowBean = new WorkflowBean();
            workflowBean.setParentUid(workflowBeanDto.getUid());
            workflowBean.setAssignee(initiator);
            workflowBean.setReceiver(userManagerService.get(Long.valueOf(uid)));
            workflowBean.setDescription(workflowBeanDto.getDescription());
            workflowBean.setTaskBean(workflowBeanDto.getTaskBean());
            workflowBean.setAssignDate(DateUtils.nowDate());
            workflowBean.setState(stateManagerService.get(0L));
            workflowBean.setWorkflowNote("");
            workflowManagerService.assignTaskToUser(workflowBean);
        }
        request.getSession().setAttribute("checks", null);
        request.getSession().setAttribute("workflowID", null);
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        String workflowUid = request.getParameter("workflowID");
        WorkflowBeanDto workflowBeanDto = new WorkflowBeanDto();
        workflowBeanDto.setUid(Long.parseLong(workflowUid));
        String[] userUids = (String[]) request.getSession().getAttribute("checks");
        workflowBeanDto.setUserUids(userUids);
        return workflowBeanDto;
    }

    public IWorkflowManagerService getWorkflowManagerService() {
        return workflowManagerService;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    public void setStateManagerService(IStateManagerService stateManagerService) {
        this.stateManagerService = stateManagerService;
    }
}
