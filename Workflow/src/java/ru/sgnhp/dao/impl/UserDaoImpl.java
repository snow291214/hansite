package ru.sgnhp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import ru.sgnhp.dao.IUserDao;
import ru.sgnhp.domain.WorkflowUser;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class UserDaoImpl extends SimpleJdbcDaoSupport implements IUserDao {

    private static String INSERT = "Insert Into users(`Login`,`LastName`,`FirstName`,`MiddleName`,`Email`) Values(?,?,?,?,?)";
    private static String UPDATE = "Update users set `LastName`=?,`FirstName`=?,`MiddleName`=?,`Email`=? where `Uid`=?";
    private static String SELECT = "Select * from users";
    private IWorkflowManagerService workflowManagerService;

    public void save(WorkflowUser user) {
        getSimpleJdbcTemplate().update(INSERT, user.getLogin(), user.getLastName(),
                user.getFirstName(), user.getMiddleName(), user.getEmail());
    }

    public void update(WorkflowUser user) {
        getSimpleJdbcTemplate().update(UPDATE, user.getLastName(), user.getFirstName(), user.getMiddleName(),
                user.getEmail(), user.getGroupUid());
    }

    public WorkflowUser getByUid(String userUid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public WorkflowUser getByLogin(String login) {
        List<WorkflowUser> users = getSimpleJdbcTemplate().query("Select * From users Where Login=?", new UserMapper(workflowManagerService), login);
        if (users.size() > 0) {
            return (WorkflowUser) users.toArray()[0];
        } else {
            return null;
        }
    }

    public WorkflowUser getByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(WorkflowUser user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteByUid(String userUid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<WorkflowUser> getAllUsers() {
        List<WorkflowUser> users = getSimpleJdbcTemplate().query(SELECT + " order by LastName", new UserMapper(workflowManagerService));
        return users;
    }

    public List<WorkflowUser> getNormalisedUsers() {
        List<WorkflowUser> users = getSimpleJdbcTemplate().query(SELECT + " where LastName <> '' order by LastName", new UserMapper(workflowManagerService));
        return users;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    private static class UserMapper implements ParameterizedRowMapper<WorkflowUser> {
    
    private final IWorkflowManagerService workflowManagerService;

    UserMapper(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    public WorkflowUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        WorkflowUser user = new WorkflowUser();
        user.setUid(rs.getLong("Uid"));
        user.setLogin(rs.getString("Login"));
        user.setLastName(rs.getString("LastName"));
        user.setMiddleName(rs.getString("MiddleName"));
        user.setFirstName(rs.getString("FirstName"));
        user.setEmail(rs.getString("Email"));
        user.setWorkflows(workflowManagerService.getAssignedWorkflows(user));
        return user;
    }
    }
}
