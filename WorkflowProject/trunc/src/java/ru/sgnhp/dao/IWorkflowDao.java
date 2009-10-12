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

    List<WorkflowBean> getRecievedWorkflowsByUserUid(Long userUid);

    List<WorkflowBean> getAssignedWorkflowsByUserUid(Long userUid);

    List<WorkflowBean> getCompletedWorkflowsByUserUid(Long userUid);

    void updateWorkflow(WorkflowBean _workflow);

    void updateWorkflowState(WorkflowBean _workflow);
}
