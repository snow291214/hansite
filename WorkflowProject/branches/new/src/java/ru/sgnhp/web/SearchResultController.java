package ru.sgnhp.web;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.dto.SearchTaskDto;
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

    private List<WorkflowBean> setWorkflowsToListOfTasks(List<TaskBean> taskBeans) {
        List<WorkflowBean> workflowBeans = null;
        if (taskBeans != null) {
            for (TaskBean b : taskBeans) {
                Long uid = b.getUid();
                if (workflowBeans == null) {
                    workflowBeans = workflowManagerService.getWorkflowsByTaskUid(uid);
                } else {
                    List<WorkflowBean> beans = workflowManagerService.getWorkflowsByTaskUid(uid);
                    if (beans != null) {
                        workflowBeans.addAll(beans);
                    } else {
                        Logger logger = Logger.getLogger(this.getClass().getName());
                        logger.info("Warning! Task without workflows has found!!! Task uid: " + uid.toString());
                    }
                }
            }
        }
        return workflowBeans;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "searchResult";
        List<WorkflowBean> workflowBeans = null;
        List<TaskBean> taskBeans = null;
        SearchTaskDto searchTaskBean = (SearchTaskDto) request.getSession().getAttribute("searchTaskBean");
        switch (searchTaskBean.getSearchType()) {
            case 0:
                //Find By Internal Number
                taskBeans = taskManagerService.getTaskByInternalNumber(
                        Integer.parseInt(searchTaskBean.getTaskInternalNumber()));
                workflowBeans = this.setWorkflowsToListOfTasks(taskBeans);
                //request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 1:
                //Find By Incoming Number
                taskBeans = taskManagerService.getTaskByIncomingNumber(
                        Integer.parseInt(searchTaskBean.getTaskIncomingNumber()));
                workflowBeans = this.setWorkflowsToListOfTasks(taskBeans);
                //request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 2:
                //Find By Assignee
                taskBeans = taskManagerService.getTasksByExternalAssignee(searchTaskBean.getAssigneeName());
                workflowBeans = this.setWorkflowsToListOfTasks(taskBeans);
                //request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 3:
                //Find By description
                taskBeans = taskManagerService.getTasksByDescription(searchTaskBean.getTaskDescription());
                workflowBeans = this.setWorkflowsToListOfTasks(taskBeans);
                //request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 4:
                //Find By Period Of Date
                WorkflowUserBean workflowUserBean = (WorkflowUserBean) request.getSession().getAttribute("initiator");
                workflowBeans = workflowManagerService.getWorkflowsByPeriodOfDate(workflowUserBean.getUid(),
                        searchTaskBean.getReceiverUid(), searchTaskBean.getStartDate(),
                        searchTaskBean.getFinishDate());
                break;
            case 5:
                taskBeans = taskManagerService.getTaskByExternalNumber(searchTaskBean.getTaskExternalNumber());
                workflowBeans = this.setWorkflowsToListOfTasks(taskBeans);
                break;
            case 6:
                taskBeans = taskManagerService.getTaskByExternalCompany(searchTaskBean.getExternalCompany());
                workflowBeans = this.setWorkflowsToListOfTasks(taskBeans);
                break;
            case 7:
                taskBeans = taskManagerService.getTaskByPrimaveraUid(searchTaskBean.getPrimaveraUid());
                workflowBeans = this.setWorkflowsToListOfTasks(taskBeans);
                break;
            default:
                result = "searchResult";
                break;
        }
        return new ModelAndView(result, "workflowBeans", workflowBeans);
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }
}
