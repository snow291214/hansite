package ru.sgnhp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 * Created on: 04.03.2010
 *
 *****
 */
public class SkippedUsersController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String workflowUid = request.getParameter("workflowID");
        request.getSession().setAttribute("checks", null);
        return new ModelAndView(new RedirectView("selectUsers.htm"), "workflowID", workflowUid);
    }
}
