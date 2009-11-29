package ru.sgnhp.service;

import java.util.List;
import ru.sgnhp.domain.TaskBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface ITaskManagerService extends IGenericService<TaskBean, Long>{

    TaskBean getTaskByInternalNumber(int number);

    TaskBean getTaskByExternalNumber(String number);

    TaskBean getTaskByIncomingNumber(int number);

    List<TaskBean> getTasksByExternalAssignee(String externalAssignee);

    int getNewInternalNumber();

    int getNewIncomingNumber();

    List<TaskBean> getTasksByDescription(String description);
}