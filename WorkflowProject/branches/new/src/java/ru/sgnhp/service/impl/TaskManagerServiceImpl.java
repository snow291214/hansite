package ru.sgnhp.service.impl;

import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.ITaskDao;
import ru.sgnhp.domain.TaskBean;
import ru.sgnhp.service.ITaskManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
@Transactional(readOnly = true)
public class TaskManagerServiceImpl extends GenericServiceImpl<TaskBean, Long> implements ITaskManagerService {

    private ITaskDao taskDao;

    public TaskManagerServiceImpl(IGenericDao<TaskBean, Long> genericDao) {
        super(genericDao);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public TaskBean getTaskByInternalNumber(int number) {
        return taskDao.getTaskByInternalNumber(number);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public TaskBean getTaskByExternalNumber(String number) {
        return taskDao.getTaskByExternalNumber(number);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public TaskBean getTaskByIncomingNumber(int number) {
        return taskDao.getTaskByIncomingNumber(number);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<TaskBean> getTasksByExternalAssignee(String externalAssignee) {
        return taskDao.getTasksByExternalAssignee("%" + externalAssignee + "%");
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getNewInternalNumber() {
        return taskDao.getNewInternalNumber()+1;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getNewIncomingNumber() {
        return taskDao.getNewIncomingNumber()+1;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<TaskBean> getTasksByDescription(String description) {
        return taskDao.getTasksByDescription("%" + description + "%");
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void setTaskDao(ITaskDao taskDao) {
        this.taskDao = taskDao;
    }
}
