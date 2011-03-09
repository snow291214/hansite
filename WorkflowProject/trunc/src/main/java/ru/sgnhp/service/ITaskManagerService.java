package ru.sgnhp.service;

import java.text.ParseException;
import java.util.List;
import ru.sgnhp.domain.TaskBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public interface ITaskManagerService extends IGenericService<TaskBean, Long> {

    TaskBean saveEx(TaskBean taskBean);

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

    List<String> getDistinctPrimaveraIDS();

    List<String> getDistinctExternalCompanies(String query);

    List<String> getExternalAssignees(String query);
}
