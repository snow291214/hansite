package ru.sgnhp.service.impl;

import java.util.List;
import ru.sgnhp.dao.ITaskDao;
import ru.sgnhp.domain.TaskBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.ITaskManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class TaskManagerServiceImpl implements ITaskManagerService {

    private ITaskDao taskDao;

    public TaskBean saveTask(TaskBean task) {
        taskDao.saveTask(task);
        return taskDao.getTaskByInternalNumber(task.getInternalNumber());
    }

    public void updateTask(TaskBean task) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void closeTask(TaskBean task) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TaskBean getTaskByUid(Long uid) {
        return taskDao.getTaskByUid(uid);
    }

    public void assignTaskToUser(String taskInternalNumber, Long initiatorUid, Long recipientUid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<TaskBean> getTasksByUser(WorkflowUserBean user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ITaskDao getTaskDao() {
        return taskDao;
    }

    public void setTaskDao(ITaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public int getTaskNewNumber() {
        return taskDao.getTaskNewNumber();
    }

    public int getIncomingNewNumber() {
        return taskDao.getIncomingNewNumber();
    }

    public TaskBean getTaskByIncomingNumber(int number) {
        return taskDao.getTaskByIncomingNumber(number);
    }

    public List<TaskBean> getTasksByExternalAssignee(String externalAssignee) {
        return taskDao.getTasksByExternalAssignee(externalAssignee);
    }
}
