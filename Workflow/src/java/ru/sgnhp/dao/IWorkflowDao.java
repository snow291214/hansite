package ru.sgnhp.dao;

import ru.sgnhp.domain.Workflow;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IWorkflowDao {
    void saveWorkflow(Workflow _workflow);
}
