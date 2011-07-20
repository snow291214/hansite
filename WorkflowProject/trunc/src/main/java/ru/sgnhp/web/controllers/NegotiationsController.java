package ru.sgnhp.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.INegotiationService;
import ru.sgnhp.service.IUserManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class NegotiationsController implements Controller {

    private IUserManagerService userManagerService;
    private INegotiationService negotiationService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        WorkflowUserBean user = getUserManagerService().getUserByLogin(currentUser);
        
        return new ModelAndView("negotiations", "negotiations", negotiationService.findNegotiationsByUser(user));
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

    /**
     * @return the negotiationService
     */
    public INegotiationService getNegotiationService() {
        return negotiationService;
    }

    /**
     * @param negotiationService the negotiationService to set
     */
    public void setNegotiationService(INegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }
}
