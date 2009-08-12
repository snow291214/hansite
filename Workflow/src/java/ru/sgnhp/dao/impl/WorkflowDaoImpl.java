package ru.sgnhp.dao.impl;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import ru.sgnhp.dao.IWorkflowDao;
import ru.sgnhp.domain.Workflow;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class WorkflowDaoImpl extends SimpleJdbcDaoSupport implements IWorkflowDao{

    private static String INSERT = "Insert Into workflows(`TaskUid`,`ParentUserUid`,`UserUid`,`Description`,`State`,`AssignDate`,`FinishDate`) Values(?,?,?,?,?,?,?)";
    
    public void saveWorkflow(Workflow _workflow) {
        getSimpleJdbcTemplate().update(INSERT, _workflow.getTaskUid(), _workflow.getParentUserUid(),
                _workflow.getUserUid(), _workflow.getDescription(),
                _workflow.getState(), _workflow.getAssignDate(), _workflow.getFinishDate());
    }

}
