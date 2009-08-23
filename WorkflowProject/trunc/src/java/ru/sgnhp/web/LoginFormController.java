package ru.sgnhp.web;

import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.dao.IAuthenticationDAO;
import ru.sgnhp.domain.UserLogin;
import ru.sgnhp.domain.WorkflowUser;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class LoginFormController extends SimpleFormController {

    private IAuthenticationDAO authenticationDAO;

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        WorkflowUser workflowUser = new WorkflowUser();
        workflowUser.setLogin("48han");
        /*Map attributes = authenticationDAO.authenticateUser((UserLogin) command);
        workflowUser.setLogin(attributes.get("sAMAccountName").toString());
        workflowUser.setLastName(attributes.get("sn").toString());
        workflowUser.setFirstName(attributes.get("givenName").toString().split(" ")[0]);
        workflowUser.setMiddleName(attributes.get("givenName").toString().split(" ")[1]);
        workflowUser.setEmail(attributes.get("mail").toString());*/
        request.getSession().setAttribute("initiator", workflowUser);
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        request.setAttribute("actionUrl", "login.htm");
        UserLogin userLogin = new UserLogin();
        if ((request.getRemoteAddr().startsWith("192"))) {
            userLogin.setDomain("dosnos.local");
            userLogin.setHost("sigma.dosnos.local");
            userLogin.setDn("DC=dosnos,DC=local");
        } else {
            userLogin.setDomain("snos.ru");
            userLogin.setHost("lyra.snos.ru");
            userLogin.setDn("DC=snos,DC=ru");
        }
        return userLogin;
    }

    public void setAuthenticationDAO(IAuthenticationDAO authenticationDAO) {
        this.authenticationDAO = authenticationDAO;
    }
}
