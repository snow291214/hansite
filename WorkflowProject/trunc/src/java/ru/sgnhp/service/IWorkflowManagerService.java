package ru.sgnhp.service;

import java.util.List;
import ru.sgnhp.domain.Workflow;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IWorkflowManagerService {
    void assignTaskToUser(Workflow _workflow);
    Workflow getWorkflowByUid(String workflowUid);
    List<Workflow> getRecievedWorkflowsByUid(Long uid);
    List<Workflow> getAssignedWorkflowsByParentUid(Long parentUid);
}
