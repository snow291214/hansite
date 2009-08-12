package ru.sgnhp.dao;

import java.util.List;
import ru.sgnhp.domain.Task;
import ru.sgnhp.domain.WorkflowUser;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface ITaskDao {

    void saveTask(Task task);

    void updateTask(Task task);

    void closeTask(Task task);

    Task getTaskByUid(Long uid);

    Task getTaskByInternalNumber(String number);

    Task getTaskByExternalNumber(String number);

    List<Task> getTasksByUser(WorkflowUser user);

    String getTaskNewNumber();
}
