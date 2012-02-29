package ru.sgnhp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.sgnhp.dto.SearchTaskDto;
import ru.sgnhp.domain.StateBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.dto.WorkflowBeanDto;

/**
 * ***
 *
 * @author Alexey Khudyakov @Skype: khudyakov.alexey
 *
 *****
 */
public interface IWorkflowManagerService extends IGenericService<WorkflowBean, Long> {

    WorkflowBean assignTaskToUser(WorkflowBean _workflow);

    WorkflowBean getWorkflowByUid(Long workflowUid);

    List<WorkflowBean> getWorkflowsByUserUidAndStateUids(Long userUid, Long[] stateUids);

    List<WorkflowBean> getRecievedWorkflowsByUserUid(Long uid);

    List<WorkflowBean> getRecievedWorkflows();

    List<WorkflowBean> getAssignedWorkflowsByUserUid(Long parentUid, Boolean completed);

    List<WorkflowBean> getCompletedWorkflowsByUserUid(Long parentUid);

//    void updateWorkflow(WorkflowBean _workflow);
    WorkflowBeanDto updateWorkflowState(WorkflowBeanDto beanDto, StateBean stateBean);

    public ArrayList<WorkflowBean> getWorkflowMembersByWorkflowUid(Long workflowUid, Long workflowParentUid, ArrayList roadmap);

    List<WorkflowBean> getWorkflowsByDescription(Long userUid, SearchTaskDto searchTaskBean);

    List<WorkflowBean> getWorkflowsByTaskUid(Long taskUid);

    List<WorkflowBean> getWorkflowByParentUid(Long workflowUid);

    List<WorkflowBean> getAllUncompletedByParentUserUid(Long parentUserUid);

    List<WorkflowBean> getAllUncompletedByParentUserUidEx(Long parentUserUid);

    List<WorkflowBean> getWorkflowsByPeriodOfDate(Long parentUserUid, Long userUid, Date startDate, Date finishDate);

    void taskReminder();

    void taskReport();

    void taskReportForDirector();

    void tasksForReviewReport();

    boolean isTaskAssignedToUser(Long taskUid, Long userUid);

    boolean isWorkflowActive(Long workflowUid);
}
