package ru.sgnhp.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.ConclusionBean;
import ru.sgnhp.service.IConclusionService;
import ru.sgnhp.service.IConclusionTypeService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class ConclusionApproveController implements Controller{

    private IConclusionService conclusionService;
    private IConclusionTypeService conclusionTypeService;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String conclusionUid = request.getParameter("conclusionUid");
        ConclusionBean conclusionBean = conclusionService.get(Long.parseLong(conclusionUid));
        conclusionBean.setConclusionTypeBean(conclusionTypeService.get(1L));
        conclusionBean.setDateOfConclusion(DateUtils.nowDate());
        conclusionService.save(conclusionBean);
        return new ModelAndView(new RedirectView("review.htm"));
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

    /**
     * @return the conclusionTypeService
     */
    public IConclusionTypeService getConclusionTypeService() {
        return conclusionTypeService;
    }

    /**
     * @param conclusionTypeService the conclusionTypeService to set
     */
    public void setConclusionTypeService(IConclusionTypeService conclusionTypeService) {
        this.conclusionTypeService = conclusionTypeService;
    }
    
}
