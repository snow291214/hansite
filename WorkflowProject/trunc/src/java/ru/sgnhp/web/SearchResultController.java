package ru.sgnhp.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.domain.SearchTaskBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IWorkflowManagerService;


public class SearchResultController implements Controller{
    private IWorkflowManagerService workflowManagerService;

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WorkflowUserBean user = (WorkflowUserBean) request.getSession().getAttribute("initiator");
        SearchTaskBean searchTaskBean  = (SearchTaskBean)request.getSession().getAttribute("searchTaskBean");
        List<WorkflowBean> workflowBeans = workflowManagerService.getWorkflowsByDescription(user.getUid(), searchTaskBean);
        request.getSession().setAttribute("searchTaskBean", null);
        return new ModelAndView("searchResult", "workflowBeans", workflowBeans);
    }

}
