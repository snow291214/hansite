package ru.sgnhp.service;

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
public interface ITaskManagerService {

    Task saveTask(Task task);

    void updateTask(Task task);

    void closeTask(Task task);

    Task getTaskByUid(Long uid);

    List<Task> getTasksByUser(WorkflowUser user);

    public String getTaskNewNumber();
}
