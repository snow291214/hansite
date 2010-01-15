package ru.sgnhp.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;
import ru.sgnhp.dto.SearchTaskDto;

public class SearchTaskFormController extends SimpleFormController {

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
        super.initBinder(request,binder);
    }

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
        searchTaskBean.setStartDate(DateUtils.increaseDate(DateUtils.nowDate(), -30));
        searchTaskBean.setFinishDate(DateUtils.nowDate());
        return searchTaskBean;
    }
}
