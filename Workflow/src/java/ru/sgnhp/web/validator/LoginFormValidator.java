package ru.sgnhp.web.validator;

import java.util.Map;
import javax.naming.directory.Attributes;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.sgnhp.dao.IAuthenticationDAO;
import ru.sgnhp.domain.UserLogin;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class LoginFormValidator implements Validator{

    private IAuthenticationDAO authenticationDAO;

    public boolean supports(Class clazz) {
        return clazz.equals(UserLogin.class);
    }

    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.login.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "login.password.empty");
        Map att = authenticationDAO.authenticateUser((UserLogin)object);
        if(att == null){
            errors.rejectValue("login", "login.login.not-found");
        }
    }

    public void setAuthenticationDAO(IAuthenticationDAO authenticationDAO) {
        this.authenticationDAO = authenticationDAO;
    }

}
