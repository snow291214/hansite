package ru.sgnhp.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.domain.WorkflowUser;
import ru.sgnhp.service.IUserManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class SelectUsersFormController extends AbstractController {

    private IUserManagerService userManagerService;

    public IUserManagerService getUserManagerService() {
        return userManagerService;
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("actionUrl", "selectUsers.htm");
        String[] checks = request.getParameterValues("checks");
        if ((checks != null) && (checks.length > 0)) {
            request.getSession().setAttribute("checks", checks);
            return new ModelAndView(new RedirectView("registerTask.htm"));
        } else {
            List<WorkflowUser> users = userManagerService.getAllNormalizedUsers();
            return new ModelAndView("selectUsers", "users", users);
        }
    }
}
