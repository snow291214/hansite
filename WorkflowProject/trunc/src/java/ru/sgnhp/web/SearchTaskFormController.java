package ru.sgnhp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.domain.SearchTaskBean;

public class SearchTaskFormController extends SimpleFormController{

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        request.getSession().setAttribute("searchTaskBean",command);
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        SearchTaskBean searchTaskBean = new SearchTaskBean();
        searchTaskBean.setIsTaskInternalNumber(Boolean.FALSE);
        searchTaskBean.setIsTaskDescription(Boolean.TRUE);
        return  searchTaskBean;
    }
}
