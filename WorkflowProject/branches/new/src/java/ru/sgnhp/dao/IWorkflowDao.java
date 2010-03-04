package ru.sgnhp.dao;

import java.util.Date;
import java.util.List;
import ru.sgnhp.domain.StateBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.dto.WorkflowBeanDto;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IWorkflowDao extends IGenericDao<WorkflowBean, Long> {

    List<WorkflowBean> getWorkflowByParentUid(Long parentUid);

    List<WorkflowBean> getByAssignDate(Date assignedDate);

    List<WorkflowBean> getByFinishDate(Date finishDate);

    List<WorkflowBean> getRecievedWorkflows();

    List<WorkflowBean> getWorkflowsByUserUidAndStateUids(Long userUid, Long[] stateUids);

    List<WorkflowBean> getRecievedWorkflowsByUserUid(Long userUid);

    List<WorkflowBean> getAssignedWorkflowsByUserUid(Long userUid, Boolean completed);

    List<WorkflowBean> getCompletedWorkflowsByUserUid(Long userUid);

    List<WorkflowBean> getWorkflowsByTaskUid(Long taskUid);

    List<WorkflowBean> getWorkflowsByDescription(Long userUid, String description);

//    void updateWorkflow(WorkflowBean workflowBean);

    WorkflowBeanDto updateWorkflowState(WorkflowBeanDto beanDto, StateBean stateBean);

    List<WorkflowBean> getAllUncompletedByParentUserUid(Long parentUserUid);

    List<WorkflowBean> getWorkflowsByPeriodOfDate(Long parentUserUid, Long userUid, Date startDate, Date finishDate);

    boolean isTaskAssignedToUser(Long taskUid, Long userUid);
}
