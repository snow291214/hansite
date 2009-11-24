package ru.sgnhp.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.domain.SearchTaskBean;
import ru.sgnhp.domain.TaskBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.ITaskManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

public class SearchResultController implements Controller {

    private IWorkflowManagerService workflowManagerService;
    private ITaskManagerService taskManagerService;

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "index";
        List<WorkflowBean> workflowBeans = null;
        List<TaskBean> taskBeans = null;
        //WorkflowUserBean user = (WorkflowUserBean) request.getSession().getAttribute("initiator");
        SearchTaskBean searchTaskBean = (SearchTaskBean) request.getSession().getAttribute("searchTaskBean");
        switch (searchTaskBean.getSearchType()) {
            case 0:
                //Find By Internal Number
                result = "searchResult";
                //workflowBeans = workflowManagerService.getWorkflowsByDescription(user.getUid(), searchTaskBean);
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 1:
                //Find By Incoming Number
                result = "searchResult";
                TaskBean taskBean = taskManagerService.getTaskByIncomingNumber(
                        Integer.parseInt(searchTaskBean.getTaskIncomingNumber()));
                workflowBeans = workflowManagerService.getWorkflowsByTaskUid(taskBean.getUid());
                //workflowBeans = workflowManagerService.getWorkflowsByDescription(user.getUid(), searchTaskBean);
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 2:
                //Find By Assignee
                result = "searchResult";
                taskBeans = taskManagerService.getTasksByExternalAssignee(searchTaskBean.getAssigneeName());
                for (TaskBean b : taskBeans) {
                    Long uid = b.getUid();
                    if (workflowBeans == null) {
                        workflowBeans = workflowManagerService.getWorkflowsByTaskUid(uid);
                    } else {
                        workflowBeans.addAll(workflowManagerService.getWorkflowsByTaskUid(uid));
                    }
                }
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 3:
                //Find By description
                result = "searchResult";
                taskBeans = taskManagerService.getTasksByDescription(searchTaskBean.getTaskDescription());
                for (TaskBean b : taskBeans) {
                    Long uid = b.getUid();
                    if (workflowBeans == null) {
                        workflowBeans = workflowManagerService.getWorkflowsByTaskUid(uid);
                    } else {
                        workflowBeans.addAll(workflowManagerService.getWorkflowsByTaskUid(uid));
                    }
                }
                request.getSession().setAttribute("searchTaskBean", null);
//                workflowBeans = workflowManagerService.getWorkflowsByDescription(user.getUid(), searchTaskBean);
//                request.getSession().setAttribute("searchTaskBean", null);
                break;
        }
        return new ModelAndView(result, "workflowBeans", workflowBeans);
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }
}
