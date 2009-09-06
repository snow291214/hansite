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

    List<WorkflowBean> getRecievedWorkflowsByUid(Long uid);

    List<WorkflowBean> getAssignedWorkflowsByParentUid(Long parentUid);

    void updateWorkflow(WorkflowBean _workflow);
}
