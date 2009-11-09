package ru.sgnhp.service;

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
public interface ITaskManagerService {

    TaskBean saveTask(TaskBean task);

    void updateTask(TaskBean task);

    void closeTask(TaskBean task);

    TaskBean getTaskByUid(Long uid);

    List<TaskBean> getTasksByUser(WorkflowUserBean user);

    int getTaskNewNumber();
    
    int getIncomingNewNumber();
}
