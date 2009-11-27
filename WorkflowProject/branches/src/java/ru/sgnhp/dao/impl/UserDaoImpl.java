package ru.sgnhp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import ru.sgnhp.dao.IUserDao;
import ru.sgnhp.domain.WorkflowUserBean;
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
    private static String UPDATE = "Update users set `LastName`=?,`FirstName`=?,`MiddleName`=?,`Email`=?, `SessionUid=?` where `Uid`=?";
    private static String SELECT = "Select * from users";

    private IWorkflowManagerService workflowManagerService;

    public void save(WorkflowUserBean user) {
        getSimpleJdbcTemplate().update(INSERT, user.getLogin(), user.getLastName(),
                user.getFirstName(), user.getMiddleName(), user.getEmail());
    }

    public void update(WorkflowUserBean user) {
        getSimpleJdbcTemplate().update(UPDATE, user.getLastName(), user.getFirstName(), user.getMiddleName(),
                user.getEmail(), user.getSessionUid(), user.getUid());
    }

    public WorkflowUserBean getByUid(Long userUid) {
        List<WorkflowUserBean> users = getSimpleJdbcTemplate().query("Select * From users Where Uid=?", new UserMapper(workflowManagerService), userUid);
        if (users.size() > 0) {
            return (WorkflowUserBean) users.toArray()[0];
        } else {
            return null;
        }
    }

    public WorkflowUserBean getByLogin(String login) {
        List<WorkflowUserBean> users = getSimpleJdbcTemplate().query("Select * From users Where Login=?", new UserMapper(workflowManagerService), login);
        if (users.size() > 0) {
            return (WorkflowUserBean) users.toArray()[0];
        } else {
            return null;
        }
    }

    public WorkflowUserBean getByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(WorkflowUserBean user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteByUid(Long userUid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<WorkflowUserBean> getAllUsers() {
        List<WorkflowUserBean> users = getSimpleJdbcTemplate().query(SELECT + " order by LastName", new UserMapper(workflowManagerService));
        return users;
    }

    public List<WorkflowUserBean> getNormalisedUsers() {
        List<WorkflowUserBean> users = getSimpleJdbcTemplate().query(SELECT + " where LastName <> '' order by LastName", new UserMapper(workflowManagerService));
        return users;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    private static class UserMapper implements ParameterizedRowMapper<WorkflowUserBean> {

        private final IWorkflowManagerService workflowManagerService;

        UserMapper(IWorkflowManagerService workflowManagerService) {
            this.workflowManagerService = workflowManagerService;
        }

        public WorkflowUserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
            WorkflowUserBean user = new WorkflowUserBean();
            user.setUid(rs.getLong("Uid"));
            user.setLogin(rs.getString("Login"));
            user.setLastName(rs.getString("LastName"));
            user.setMiddleName(rs.getString("MiddleName"));
            user.setFirstName(rs.getString("FirstName"));
            user.setEmail(rs.getString("Email"));
            user.setSessionUid("SessionUid");
            return user;
        }
    }
}
