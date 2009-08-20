package ru.sgnhp.service.impl;

import java.util.List;
import ru.sgnhp.dao.ITaskDao;
import ru.sgnhp.domain.Task;
import ru.sgnhp.domain.WorkflowUser;
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

    public Task saveTask(Task task) {
        taskDao.saveTask(task);
        return taskDao.getTaskByInternalNumber(task.getInternalNumber());
    }

    public void updateTask(Task task) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void closeTask(Task task) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Task getTaskByUid(Long uid) {
        return taskDao.getTaskByUid(uid);
    }

    public void assignTaskToUser(String taskInternalNumber, Long initiatorUid, Long recipientUid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Task> getTasksByUser(WorkflowUser user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ITaskDao getTaskDao() {
        return taskDao;
    }

    public void setTaskDao(ITaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public String getTaskNewNumber() {
        return taskDao.getTaskNewNumber();
    }
}
