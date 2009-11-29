package ru.sgnhp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import ru.sgnhp.DateUtils;
import ru.sgnhp.dao.IWorkflowDao;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.service.ITaskManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class WorkflowDaoImpl extends SimpleJdbcDaoSupport implements IWorkflowDao {

    private static String INSERT = "Insert Into workflows(`ParentUid`,`TaskUid`," +
            "`ParentUserUid`,`UserUid`,`Description`,`State`,`AssignDate`," +
            "`FinishDate`,`WorkflowNote`) Values(?,?,?,?,?,?,?,?,?)";
    private static String SELECT = "SELECT * FROM workflows";
    private static String UPDATE = "Update workflows set `ParentUid` = ?, " +
            "`TaskUid` = ?, `ParentUserUid` = ?, `UserUid` = ?,`Description` = ?, " +
            "`State` = ?,`AssignDate` = ?,`FinishDate` = ? `WorkflowNote` = ? " +
            "where uid = ?";
    private ITaskManagerService taskManagerService;

    public void saveWorkflow(WorkflowBean _workflow) {
        getSimpleJdbcTemplate().update(INSERT, _workflow.getParentUid(), _workflow.getTaskUid(), _workflow.getParentUserUid(),
                _workflow.getUserUid(), _workflow.getDescription(),
                _workflow.getState(), DateUtils.stringToDate(_workflow.getAssignDate(), "yyyy-MM-dd"),
                DateUtils.stringToDate(_workflow.getFinishDate(), "yyyy-MM-dd"),
                _workflow.getWorkflowNote());
    }

    public List<WorkflowBean> getRecievedWorkflowsByUserUid(Long userUid) {
        List<WorkflowBean> workflows = getSimpleJdbcTemplate().query(SELECT +
                ", state  WHERE  workflows.UserUid = ? And " +
                "state.StateUid = workflows.state and workflows.state in (0,2) order by Uid Desc",
                new WorkflowMapper(taskManagerService), userUid);
        return workflows;
    }

    public List<WorkflowBean> getAssignedWorkflowsByUserUid(Long userUid, Boolean completed) {
        String selectString = "";
        if (!completed) {
            selectString = SELECT + ",state  WHERE  workflows.ParentUserUid = ? And " +
                    "state.StateUid = workflows.state  and workflows.state<>3  order by Uid Desc";
        } else {
            selectString = SELECT + ",state  WHERE  workflows.ParentUserUid = ? And " +
                    "state.StateUid = workflows.state and workflows.state=3  order by Uid Desc";
        }
        List<WorkflowBean> workflows = getSimpleJdbcTemplate().query(selectString,
                new WorkflowMapper(taskManagerService), userUid);
        return workflows;
    }

    public List<WorkflowBean> getCompletedWorkflowsByUserUid(Long userUid) {
        List<WorkflowBean> workflows = getSimpleJdbcTemplate().query(SELECT +
                ", state  WHERE  workflows.UserUid = ? And " +
                "state.StateUid = workflows.state and workflows.state = 3 order by Uid Desc",
                new WorkflowMapper(taskManagerService), userUid);
        return workflows;
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    public WorkflowBean getWorkflowByUid(Long workflowUid) {
        List<WorkflowBean> workflows = getSimpleJdbcTemplate().query(SELECT +
                ",state  WHERE  workflows.Uid = ? And state.StateUid = workflows.state " +
                "order by Uid Desc",
                new WorkflowMapper(taskManagerService), workflowUid);
        if (workflows.size() > 0) {
            return (WorkflowBean) workflows.toArray()[0];
        } else {
            return null;
        }
    }

    public WorkflowBean getWorkflowByParentUid(Long parentUid) {
        List<WorkflowBean> workflows = getSimpleJdbcTemplate().query(SELECT +
                ",state  WHERE  workflows.ParentUid = ? And state.StateUid = workflows.state " +
                "order by Uid Desc",
                new WorkflowMapper(taskManagerService), parentUid);
        if (workflows.size() > 0) {
            return (WorkflowBean) workflows.toArray()[0];
        } else {
            return null;
        }
    }

    public void updateWorkflow(WorkflowBean _workflow) {
        getSimpleJdbcTemplate().update(UPDATE, _workflow.getParentUid(), _workflow.getTaskUid(), _workflow.getParentUserUid(),
                _workflow.getUserUid(), _workflow.getDescription(),
                _workflow.getState(), DateUtils.stringToDate(_workflow.getAssignDate(), "yyyy-MM-dd"),
                DateUtils.stringToDate(_workflow.getFinishDate(), "yyyy-MM-dd"), _workflow.getUid());
    }

    public void updateWorkflowState(WorkflowBean _workflow) {
        //this.saveWorkflow(_workflow);
        getSimpleJdbcTemplate().update("Update workflows set State = ?, WorkflowNote = ? where Uid = ?",
                _workflow.getState(), _workflow.getWorkflowNote(), _workflow.getUid());
    }

    public int getRecievedWorkflowsCountByUserUid(Long userUid) {
        return getSimpleJdbcTemplate().queryForInt("SELECT count(*) from workflows" +
                ", state  WHERE  workflows.UserUid = ? And " +
                "state.StateUid = workflows.state and workflows.state in (0,2)",
                userUid);
    }

    public int getAssignedWorkflowsCountByUserUid(Long userUid) {
        return getSimpleJdbcTemplate().queryForInt("SELECT count(*) from workflows" +
                ",state  WHERE  workflows.ParentUserUid = ? And " +
                "state.StateUid = workflows.state",
                userUid);
    }

    public int getCompletedWorkflowsCountByUserUid(Long userUid) {
        return getSimpleJdbcTemplate().queryForInt("SELECT count(*) from workflows" +
                ", state  WHERE  workflows.UserUid = ? And " +
                "state.StateUid = workflows.state and workflows.state = 3",
                userUid);
    }

    public List<WorkflowBean> getWorkflowsByDescription(Long userUid, String description) {
        List<WorkflowBean> workflows = getSimpleJdbcTemplate().query(SELECT +
                ", state  WHERE  workflows.UserUid = ? And " +
                "state.StateUid = workflows.State " +
                "And workflows.Description like ? order by Uid Desc",
                new WorkflowMapper(taskManagerService), userUid, "%" + description + "%");
        return workflows;
    }

    public List<WorkflowBean> getWorkflowsByTaskUid(Long taskUid) {
        List<WorkflowBean> workflows = getSimpleJdbcTemplate().query(SELECT +
                ", state  WHERE state.StateUid = workflows.State " +
                "And workflows.TaskUid=? And workflows.ParentUid=-1 order by Uid Desc",
                new WorkflowMapper(taskManagerService), taskUid);
        return workflows;
    }

    private static class WorkflowMapper implements ParameterizedRowMapper<WorkflowBean> {

        private ITaskManagerService taskManagerService;

        private WorkflowMapper() {
        }

        private WorkflowMapper(ITaskManagerService taskManagerService) {
            this.taskManagerService = taskManagerService;
        }

        public WorkflowBean mapRow(ResultSet rs, int rowNum) throws SQLException {
            WorkflowBean workflow = new WorkflowBean();
            workflow.setUid(rs.getLong("Uid"));
            workflow.setParentUid(rs.getLong("ParentUid"));
            workflow.setTaskUid(rs.getLong("TaskUid"));
            workflow.setParentUserUid(rs.getLong("ParentUserUid"));
            workflow.setUserUid(rs.getLong("UserUid"));
            workflow.setDescription(rs.getString("Description"));
            workflow.setState(rs.getString("StateDescription"));
            workflow.setAssignDate(rs.getString("AssignDate"));
            workflow.setFinishDate(rs.getString("FinishDate"));
            workflow.setWorkflowNote(rs.getString("WorkflowNote"));
            workflow.setTask(taskManagerService.get(workflow.getTaskUid()));
            return workflow;
        }
    }
}
