package ru.sgnhp.web.forms;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.domain.UserGroupBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.UserLogin;
import ru.sgnhp.service.IUserGroupService;
import ru.sgnhp.service.IUserManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class NewUserFormController extends SimpleFormController {

    private IUserManagerService userManagerService;
    private IUserGroupService userGroupService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        List<UserGroupBean> userGroupBeans = userGroupService.getAll();
        request.setAttribute("groups", userGroupBeans);
        UserLogin userLogin = new UserLogin();
        return userLogin;
    }

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        String groupUid = request.getParameter("groupUid");
        UserLogin userLogin = (UserLogin)command;
        WorkflowUserBean workflowUserBean = new WorkflowUserBean();
        workflowUserBean.setLogin(userLogin.getUsername());
        workflowUserBean.setFirstName(userLogin.getFirstName());
        workflowUserBean.setLastName(userLogin.getLastName());
        workflowUserBean.setMiddleName(userLogin.getMiddleName());
        workflowUserBean.setEnabled(Boolean.TRUE);
        workflowUserBean.setEmail(userLogin.getEmail());
        UserGroupBean userGroupBean = userGroupService.get(Long.parseLong(groupUid));
        workflowUserBean.setUserGroupBean(userGroupBean);
        userManagerService.save(workflowUserBean);
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    public IUserManagerService getUserManagerService() {
        return userManagerService;
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    public IUserGroupService getUserGroupService() {
        return userGroupService;
    }

    public void setUserGroupService(IUserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }
}
