package ru.sgnhp.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IConclusionService;
import ru.sgnhp.service.IUserManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class NegotiationForReviewController implements Controller {

    private IUserManagerService userManagerService;
    private IConclusionService conclusionService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        WorkflowUserBean user = userManagerService.getUserByLogin(currentUser);     
        return new ModelAndView("negotiationsForReview", "conclusions", conclusionService.findActiveConclusionsByUser(user));
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
     * @return the conclusionService
     */
    public IConclusionService getConclusionService() {
        return conclusionService;
    }

    /**
     * @param conclusionService the conclusionService to set
     */
    public void setConclusionService(IConclusionService conclusionService) {
        this.conclusionService = conclusionService;
    }
}
