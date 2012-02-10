package ru.sgnhp.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.domain.Department;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IDepartmentService;
import ru.sgnhp.service.IUserManagerService;

/**
 * ***
 *
 * @author Alexey Khudyakov @Skype: khudyakov.alexey
 *
 *****
 */
public class SelectUsersFormController extends AbstractController {

    private IUserManagerService userManagerService;
    private IDepartmentService departmentService;
    private String workflowUid;

    public IUserManagerService getUserManagerService() {
        return userManagerService;
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.handleRequest(request, response);
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("actionUrl", "selectUsers.htm");
        String[] checks = request.getParameterValues("checks");
        if ((checks != null) && (checks.length > 0)) {
            request.getSession().setAttribute("checks", checks);
            if (workflowUid == null) {
                return new ModelAndView(new RedirectView("registerTask.htm"));
            } else {
                request.getSession().setAttribute("workflowID", workflowUid);
                return new ModelAndView(new RedirectView("assignTask.htm"), "workflowID", workflowUid);
            }
        } else {
            workflowUid = request.getParameter("workflowID");
            List<Department> departments = departmentService.getAll();
            return new ModelAndView("selectUsers", "departments", departments);
            //List<WorkflowUserBean> users = userManagerService.getAll();
            //return new ModelAndView("selectUsers", "users", users);
        }
    }

    /**
     * @return the departmentService
     */
    public IDepartmentService getDepartmentService() {
        return departmentService;
    }

    /**
     * @param departmentService the departmentService to set
     */
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}
