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

public class SearchResultController implements Controller {

    private IWorkflowManagerService workflowManagerService;

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "index";
        List<WorkflowBean> workflowBeans = null;
        WorkflowUserBean user = (WorkflowUserBean) request.getSession().getAttribute("initiator");
        SearchTaskBean searchTaskBean = (SearchTaskBean) request.getSession().getAttribute("searchTaskBean");
        switch (searchTaskBean.getSearchType()) {
            case 0:
                result = "searchResult";
                //workflowBeans = workflowManagerService.getWorkflowsByDescription(user.getUid(), searchTaskBean);
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 1:
                result = "searchResult";
                //workflowBeans = workflowManagerService.getWorkflowsByDescription(user.getUid(), searchTaskBean);
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 2:
                result = "searchResult";
                //workflowBeans = workflowManagerService.getWorkflowsByDescription(user.getUid(), searchTaskBean);
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 3:
                result = "searchResult";
                workflowBeans = workflowManagerService.getWorkflowsByDescription(user.getUid(), searchTaskBean);
                request.getSession().setAttribute("searchTaskBean", null);
                break;
        }
        return new ModelAndView(result, "workflowBeans", workflowBeans);
    }
}
