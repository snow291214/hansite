package ru.sgnhp.dao;

import java.util.List;
import ru.sgnhp.domain.TaskBean;
import ru.sgnhp.domain.WorkflowUserBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface ITaskDao {

    void saveTask(TaskBean task);

    void updateTask(TaskBean task);

    void closeTask(TaskBean task);

    TaskBean getTaskByUid(Long uid);

    TaskBean getTaskByInternalNumber(int number);

    TaskBean getTaskByExternalNumber(String number);

    TaskBean getTaskByIncomingNumber(int number);

    List<TaskBean> getTasksByExternalAssignee(String externalAssignee);

    List<TaskBean> getTasksByUser(WorkflowUserBean user);

    int getTaskNewNumber();

    int getIncomingNewNumber();

    List<TaskBean> getTasksByDescription(String description);
}
