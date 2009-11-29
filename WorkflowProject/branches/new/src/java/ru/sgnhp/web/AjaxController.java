package ru.sgnhp.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.service.ITaskManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class AjaxController implements Controller {

    private ITaskManagerService taskManagerService;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/xml");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<InternalNumber>");
        response.getWriter().write("<value>" + taskManagerService.getNewIncomingNumber() + "</value>");
        response.getWriter().write("</InternalNumber>");
        return null;
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }
}
