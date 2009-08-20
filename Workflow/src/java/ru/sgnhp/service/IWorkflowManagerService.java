package ru.sgnhp.service;

import java.util.List;
import ru.sgnhp.domain.Workflow;
import ru.sgnhp.domain.WorkflowUser;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IWorkflowManagerService {
    void assignTaskToUser(Workflow _workflow);
    List<Workflow> getAssignedWorkflows(WorkflowUser user);
}
