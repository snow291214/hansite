package ru.sgnhp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class AssignTaskFormController extends SimpleFormController {

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) throws ServletException {
        return null;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        return null;
    }

}