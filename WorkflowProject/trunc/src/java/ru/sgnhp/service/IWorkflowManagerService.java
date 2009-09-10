package ru.sgnhp.service;

import java.util.List;
import ru.sgnhp.domain.WorkflowBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IWorkflowManagerService {

    void assignTaskToUser(WorkflowBean _workflow);

    WorkflowBean getWorkflowByUid(Long workflowUid);

    List<WorkflowBean> getRecievedWorkflowsByUserUid(Long uid);

    List<WorkflowBean> getAssignedWorkflowsByUserUid(Long parentUid);

    void updateWorkflow(WorkflowBean _workflow);
}
