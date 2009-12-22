package ru.sgnhp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.dto.SearchTaskDto;

public class SearchTaskFormController extends SimpleFormController {

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        SearchTaskDto searchTaskBean = (SearchTaskDto)command;
        String searchType = request.getParameter("searchType");
        searchTaskBean.setSearchType(Integer.parseInt(searchType));
        request.getSession().setAttribute("searchTaskBean", command);
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        SearchTaskDto searchTaskBean = new SearchTaskDto();
        return searchTaskBean;
    }
}
