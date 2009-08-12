package ru.sgnhp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import ru.sgnhp.DateUtils;
import ru.sgnhp.dao.ITaskDao;
import ru.sgnhp.domain.Task;
import ru.sgnhp.domain.WorkflowUser;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class TaskDaoImpl extends SimpleJdbcDaoSupport implements ITaskDao {

    private static String INSERT = "Insert Into tasks(`InternalNumber`,`ExternalNumber`,`Description`,`StartDate`,`DueDate`) Values(?,?,?,?,?)";
    private static String COUNT = "Select Count(*) As C from tasks";
    private static String SELECT = "Select * from tasks";

    public void saveTask(Task task) {

        getSimpleJdbcTemplate().update(INSERT, task.getInternalNumber(), task.getExternalNumber(),
                task.getDescription(), DateUtils.stringToDate(task.getStartDate()), DateUtils.stringToDate(task.getDueDate()));
    }

    public void updateTask(Task task) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void closeTask(Task task) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Task getTaskByUid(Long uid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Task getTaskByInternalNumber(String number) {
        List<Task> task = getSimpleJdbcTemplate().query(SELECT+" Where InternalNumber = ? ", new UserMapper(), number);
        if (task.size() > 0) {
            return (Task) task.toArray()[0];
        } else {
            return null;
        }
    }

    public Task getTaskByExternalNumber(String number) {
        List<Task> task = getSimpleJdbcTemplate().query(SELECT+" Where ExternalNumber = ? ", new UserMapper(), number);
        if (task.size() > 0) {
            return (Task) task.toArray()[0];
        } else {
            return null;
        }
    }

    public List<Task> getTasksByUser(WorkflowUser user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getTaskNewNumber() {
        String number = "";
        try {
            SqlRowSet rs = getJdbcTemplate().queryForRowSet(COUNT);
            rs.first();
            Calendar cal = Calendar.getInstance();
            String year = Integer.toString(cal.get(Calendar.YEAR));
            number = Integer.toString(rs.getInt("C") + 1);
            number = "T." + year + "-" + number;
        } catch (InvalidResultSetAccessException ex) {
            Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    private static class UserMapper implements ParameterizedRowMapper<Task> {

        public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
            Task task = new Task();
            task.setUid(rs.getLong("Uid"));
            task.setInternalNumber(rs.getString("InternalNumber"));
            task.setExternalNumber(rs.getString("ExternalNumber"));
            task.setDescription(rs.getString("Description"));
            task.setStartDate(rs.getDate("StartDate").toString());
            task.setDueDate(rs.getDate("DueDate").toString());
            return task;
        }
    }

}
