package ru.sgnhp.web.forms;

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
import ru.sgnhp.service.IUserManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

public class ExtraDescriptionFormController extends SimpleFormController {

    private IWorkflowManagerService workflowManagerService;
    private IUserManagerService userManagerService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        WorkflowBeanDto workflowBeanDto = new WorkflowBeanDto();
        workflowBeanDto.setDescription("");
        return workflowBeanDto;
    }

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        String workflowUid = request.getParameter("workflowID");
        if (workflowUid != null) {
            final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
            WorkflowUserBean initiator = userManagerService.getUserByLogin(currentUser);
            WorkflowBeanDto workflowBeanDto = (WorkflowBeanDto) command;
            WorkflowBean workflowBean = workflowManagerService.get(Long.parseLong(workflowUid));
            String extraDescription = workflowBean.getTaskBean().getDescription()
                    + ". <br>Дополнительная резолюция: '" + workflowBeanDto.getDescription()
                    + "' <br>Должностное лицо: " + initiator.getLastName() + " "
                    + initiator.getFirstName() + " " + initiator.getMiddleName()
                    + ". Дата резолюции: " + DateUtils.nowString("dd.MM.yyyy");
            workflowBean.getTaskBean().setDescription(extraDescription);
            workflowManagerService.save(workflowBean);
        }
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    public IWorkflowManagerService getWorkflowManagerService() {
        return workflowManagerService;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    /**
     * @return the userManagerService
     */
    public IUserManagerService getUserManagerService() {
        return userManagerService;
    }

    /**
     * @param userManagerService the userManagerService to set
     */
    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
