package ru.sgnhp.web.controllers;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.service.ITaskManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class AutocompleteCompaniesController implements Controller {

    private ITaskManagerService taskManagerService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        String q = request.getParameter("q");
        List<String> values = taskManagerService.getDistinctExternalCompanies(q);
        PrintWriter out = response.getWriter();
        for (String value : values) {
            out.println(value);
        }
        return null;
    }

    public ITaskManagerService getTaskManagerService() {
        return taskManagerService;
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }
}
