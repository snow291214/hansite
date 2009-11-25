package ru.sgnhp.dao;

import java.util.List;
import ru.sgnhp.domain.WorkflowBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IWorkflowDao {

    void saveWorkflow(WorkflowBean _workflow);

    WorkflowBean getWorkflowByUid(Long workflowUid);

    WorkflowBean getWorkflowByParentUid(Long parentUid);

    List<WorkflowBean> getRecievedWorkflowsByUserUid(Long userUid);

    int getRecievedWorkflowsCountByUserUid(Long userUid);

    List<WorkflowBean> getAssignedWorkflowsByUserUid(Long userUid, Boolean completed);

    int getAssignedWorkflowsCountByUserUid(Long userUid);

    List<WorkflowBean> getCompletedWorkflowsByUserUid(Long userUid);

    int getCompletedWorkflowsCountByUserUid(Long userUid);

    void updateWorkflow(WorkflowBean _workflow);

    void updateWorkflowState(WorkflowBean _workflow);

    List<WorkflowBean> getWorkflowsByTaskUid(Long taskUid);

    List<WorkflowBean> getWorkflowsByDescription(Long userUid, String description);
}
