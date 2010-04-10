package ru.sgnhp.services;

import java.text.ParseException;
import java.util.List;
import ru.sgnhp.entity.TaskBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface ITaskManagerService extends IGenericService<TaskBean, Long> {

    List<TaskBean> getAllIncomingMailByYear(Integer currentYear) throws ParseException;

    List<TaskBean> getTaskByInternalNumber(int number);

    List<TaskBean> getTaskByIncomingNumber(int number);

    List<TaskBean> getTaskByExternalNumber(String number);

    List<TaskBean> getTasksByExternalAssignee(String externalAssignee);

    List<TaskBean> getTaskByExternalCompany(String externalCompany);

    List<TaskBean> getTaskByPrimaveraUid(String primaveraUid);

    int getNewInternalNumber();

    int getNewIncomingNumber();

    List<TaskBean> getTasksByDescription(String description);

    void dailyReport();

    List<String> getAllTasksWithPrimaveraUid();
}
