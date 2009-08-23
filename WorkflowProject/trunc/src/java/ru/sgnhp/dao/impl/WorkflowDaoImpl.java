package ru.sgnhp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import ru.sgnhp.dao.IWorkflowDao;
import ru.sgnhp.domain.Workflow;
import ru.sgnhp.service.ITaskManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class WorkflowDaoImpl extends SimpleJdbcDaoSupport implements IWorkflowDao{

    private static String INSERT = "Insert Into workflows(`TaskUid`,`ParentUserUid`,`UserUid`,`Description`,`State`,`AssignDate`,`FinishDate`) Values(?,?,?,?,?,?,?)";
    private static String SELECT = "SELECT * FROM workflows";
    private ITaskManagerService taskManagerService;

    public void saveWorkflow(Workflow _workflow) {
        getSimpleJdbcTemplate().update(INSERT, _workflow.getTaskUid(), _workflow.getParentUserUid(),
                _workflow.getUserUid(), _workflow.getDescription(),
                _workflow.getState(), _workflow.getAssignDate(), _workflow.getFinishDate());
    }

    public List<Workflow> getRecievedWorkflowsByUserUid(Long userUid) {
        List<Workflow> workflows = getSimpleJdbcTemplate().query(SELECT + 
                "  WHERE  workflows.UserUid = ? order by AssignDate, State",
                new WorkflowMapper(taskManagerService), userUid);
        return workflows;
    }

    public List<Workflow> getAssignedWorkflowsByUserUid(Long userUid) {
        List<Workflow> workflows = getSimpleJdbcTemplate().query(SELECT +
                "  WHERE  workflows.ParentUserUid = ? order by AssignDate, State",
                new WorkflowMapper(taskManagerService), userUid);
        return workflows;
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    private static class WorkflowMapper implements ParameterizedRowMapper<Workflow> {
        
        private ITaskManagerService taskManagerService;

        private WorkflowMapper() {
        }

        private WorkflowMapper(ITaskManagerService taskManagerService) {
            this.taskManagerService = taskManagerService;
        }

        public Workflow mapRow(ResultSet rs, int rowNum) throws SQLException {
            Workflow workflow = new Workflow();
            workflow.setUid(rs.getLong("Uid"));
            workflow.setTaskUid(rs.getLong("TaskUid"));
            workflow.setParentUserUid(rs.getLong("ParentUserUid"));
            workflow.setUserUid(rs.getLong("UserUid"));
            workflow.setDescription(rs.getString("Description"));
            workflow.setState(rs.getShort("State"));
            workflow.setAssignDate(rs.getString("AssignDate"));
            workflow.setFinishDate(rs.getString("FinishDate"));
            workflow.setTask(taskManagerService.getTaskByUid(workflow.getTaskUid()));
            //workflow.setAssignee(userManagerService.getUserByUid(workflow.getUserUid()));
            return workflow;
        }
    }

}
