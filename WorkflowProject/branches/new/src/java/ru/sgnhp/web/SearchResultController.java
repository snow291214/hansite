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

//    private List<WorkflowBean> setWorkflowsList(List<TaskBean> taskBeans){
//
//    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "index";
        List<WorkflowBean> workflowBeans = null;
        List<TaskBean> taskBeans = null;
        //WorkflowUserBean user = (WorkflowUserBean) request.getSession().getAttribute("initiator");
        SearchTaskDto searchTaskBean = (SearchTaskDto) request.getSession().getAttribute("searchTaskBean");
        switch (searchTaskBean.getSearchType()) {
            case 0:
                //Find By Internal Number
                result = "searchResult";
                taskBeans = taskManagerService.getTaskByInternalNumber(
                        Integer.parseInt(searchTaskBean.getTaskInternalNumber()));
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
                //workflowBeans = workflowManagerService.getWorkflowsByDescription(user.getUid(), searchTaskBean);
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 1:
                //Find By Incoming Number
                result = "searchResult";
                taskBeans = taskManagerService.getTaskByIncomingNumber(
                        Integer.parseInt(searchTaskBean.getTaskIncomingNumber()));
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
                //workflowBeans = workflowManagerService.getWorkflowsByDescription(user.getUid(), searchTaskBean);
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 2:
                //Find By Assignee
                result = "searchResult";
                taskBeans = taskManagerService.getTasksByExternalAssignee(searchTaskBean.getAssigneeName());
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
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 3:
                //Find By description
                result = "searchResult";
                taskBeans = taskManagerService.getTasksByDescription(searchTaskBean.getTaskDescription());
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
                request.getSession().setAttribute("searchTaskBean", null);
//                workflowBeans = workflowManagerService.getWorkflowsByDescription(user.getUid(), searchTaskBean);
//                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 4:
                //Find By Period Of Date
                result = "searchResult";
                WorkflowUserBean workflowUserBean = (WorkflowUserBean) request.getSession().getAttribute("initiator");
                workflowBeans = workflowManagerService.getWorkflowsByPeriodOfDate(workflowUserBean.getUid(),
                        searchTaskBean.getReceiverUid(), searchTaskBean.getStartDate(),
                        searchTaskBean.getFinishDate());
                break;
            case 5:
                result = "searchResult";
                taskBeans = taskManagerService.getTaskByExternalNumber(searchTaskBean.getTaskExternalNumber());
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
                break;
        }
        return new ModelAndView(result, "workflowBeans", workflowBeans);
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }
}
