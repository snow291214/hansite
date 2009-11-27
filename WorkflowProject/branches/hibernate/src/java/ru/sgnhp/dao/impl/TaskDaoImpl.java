package ru.sgnhp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import ru.sgnhp.DateUtils;
import ru.sgnhp.dao.ITaskDao;
import ru.sgnhp.domain.TaskBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IUploadManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class TaskDaoImpl extends SimpleJdbcDaoSupport implements ITaskDao {

    private static String INSERT = "Insert Into tasks(`InternalNumber`,`IncomingNumber`,`ExternalNumber`,`ExternalCompany`,`ExternalAssignee`,`Description`,`StartDate`,`DueDate`) Values(?,?,?,?,?,?,?,?)";
    private static String maxInternalNumber = "Select Max(InternalNumber) As C from tasks";
    private static String maxIncomingNumber = "Select Max(IncomingNumber) As C from tasks";
    private static String SELECT = "Select * from tasks";
    private IUploadManagerService uploadManagerService;

    public void saveTask(TaskBean task) {
        getSimpleJdbcTemplate().update(INSERT, task.getInternalNumber(), task.getIncomingNumber(),
                task.getExternalNumber().toUpperCase(), task.getExternalCompany(),
                task.getExternalAssignee(), task.getDescription(),
                DateUtils.stringToDate(task.getStartDate(), "dd.MM.yyyy"),
                DateUtils.stringToDate(task.getDueDate(), "dd.MM.yyyy"));
    }

    public void updateTask(TaskBean task) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void closeTask(TaskBean task) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TaskBean getTaskByUid(Long uid) {
        List<TaskBean> task = getSimpleJdbcTemplate().query(SELECT + " Where Uid = ? order by InternalNumber", new UserMapper(uploadManagerService), uid);
        if (task.size() > 0) {
            return (TaskBean) task.toArray()[0];
        } else {
            return null;
        }
    }

    public TaskBean getTaskByInternalNumber(int number) {
        List<TaskBean> task = getSimpleJdbcTemplate().query(SELECT + " Where InternalNumber = ? ", new UserMapper(uploadManagerService), number);
        if (task.size() > 0) {
            return (TaskBean) task.toArray()[0];
        } else {
            return null;
        }
    }

    public TaskBean getTaskByExternalNumber(String number) {
        List<TaskBean> task = getSimpleJdbcTemplate().query(SELECT + " Where ExternalNumber = ? ", new UserMapper(uploadManagerService), number);
        if (task.size() > 0) {
            return (TaskBean) task.toArray()[0];
        } else {
            return null;
        }
    }

    public List<TaskBean> getTasksByUser(WorkflowUserBean user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*public String getTaskNewNumber() {
    String number = "";
    try {
    SqlRowSet rs = getJdbcTemplate().queryForRowSet(COUNT);
    rs.first();
    Calendar cal = Calendar.getInstance();
    String year = Integer.toString(cal.get(Calendar.YEAR));
    number = Integer.toString(rs.getInt("C") + 1);
    number = "T." + year + "." + number;
    } catch (InvalidResultSetAccessException ex) {
    Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return number;
    }*/
    public int getTaskNewNumber() {
        int number = -1;
        try {
            number = getJdbcTemplate().queryForInt(maxInternalNumber);
            number++;
        } catch (InvalidResultSetAccessException ex) {
            Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    public int getIncomingNewNumber() {
        int number = -1;
        try {
            number = getJdbcTemplate().queryForInt(maxIncomingNumber);
            number++;
        } catch (InvalidResultSetAccessException ex) {
            Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    public void setUploadManagerService(IUploadManagerService uploadManagerService) {
        this.uploadManagerService = uploadManagerService;
    }

    public TaskBean getTaskByIncomingNumber(int number) {
        List<TaskBean> task = getSimpleJdbcTemplate().query(SELECT + " Where IncomingNumber = ? ", new UserMapper(uploadManagerService), number);
        if (task.size() > 0) {
            return (TaskBean) task.toArray()[0];
        } else {
            return null;
        }
    }

    public List<TaskBean> getTasksByExternalAssignee(String externalAssignee) {
        return getSimpleJdbcTemplate().query(SELECT +
                " Where ExternalAssignee Like ? ", new UserMapper(uploadManagerService),
                "%" + externalAssignee + "%");
    }

    public List<TaskBean> getTasksByDescription(String description) {
        return getSimpleJdbcTemplate().query(SELECT +
                " Where Description Like ? ", new UserMapper(uploadManagerService),
                "%" + description + "%");
    }

    private static class UserMapper implements ParameterizedRowMapper<TaskBean> {

        private final IUploadManagerService uploadManagerService;

        private UserMapper(IUploadManagerService uploadManagerService) {
            this.uploadManagerService = uploadManagerService;
        }

        public TaskBean mapRow(ResultSet rs, int rowNum) throws SQLException {
            TaskBean task = new TaskBean();
            task.setUid(rs.getLong("Uid"));
            task.setInternalNumber(rs.getInt("InternalNumber"));
            task.setIncomingNumber(rs.getLong("IncomingNumber"));
            task.setExternalNumber(rs.getString("ExternalNumber"));
            task.setExternalCompany(rs.getString("ExternalCompany"));
            task.setExternalAssignee(rs.getString("ExternalAssignee"));
            task.setDescription(rs.getString("Description"));
            task.setStartDate(rs.getDate("StartDate").toString());
            task.setDueDate(rs.getDate("DueDate").toString());
            task.setTaskFiles(uploadManagerService.getFileUploadBeanByTaskUid(task.getUid()));
            return task;
        }
    }
}
