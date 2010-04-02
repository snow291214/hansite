package ru.sgnhp.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public List<TaskBean> getTaskByInternalNumber(int number) {
        return taskDao.getTaskByInternalNumber(number);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<TaskBean> getTaskByExternalNumber(String number) {
        return taskDao.getTaskByExternalNumber("%" + number + "%");
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<TaskBean> getTaskByIncomingNumber(int number) {
        return taskDao.getTaskByIncomingNumber(number);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<TaskBean> getTasksByExternalAssignee(String externalAssignee) {
        return taskDao.getTasksByExternalAssignee("%" + externalAssignee + "%");
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getNewInternalNumber() {
        int value = taskDao.getNewInternalNumber();
        if (value == -1) {
            value++;
        }
        return value + 1;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getNewIncomingNumber() {
        int value = taskDao.getNewIncomingNumber();
        if (value == -1) {
            value++;
        }
        return value + 1;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<TaskBean> getTasksByDescription(String description) {
        return taskDao.getTasksByDescription("%" + description + "%");
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void setTaskDao(ITaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<TaskBean> getTaskByExternalCompany(String externalCompany) {
        return taskDao.getTaskByExternalCompany("%" + externalCompany + "%");
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void dailyReport() {
        Calendar today = Calendar.getInstance();
        //File file = new File(String.format("/media/storage/doc/скан/исходящие %1$tY/%2$s/", today, "incomingMail.csv"));
        try {
            FileWriter outFile = new FileWriter(
                    String.format("/media/storage/doc/скан/входящие %1$tY/%2$s/",
                    today, "incomingMail.csv"));
            PrintWriter out = new PrintWriter(outFile);
            List<TaskBean> taskBeans = this.getAllIncomingMailByYear(today.get(Calendar.YEAR));
            out.println("UID;Внутренний номер задачи;Входящий номер;Номер в \"Documentum\";"
                    + "Компания-отправитель;Кто подписал;Описание задачи;Дата регистрации;Дата ответа");
            for (TaskBean taskBean : taskBeans) {
                out.println(String.format("%1$s;%2$s;%3$s;%4$s;%5$s;%6$s;%7$s;%8$s;%9$s;",
                        taskBean.getUid(),
                        taskBean.getInternalNumber(),
                        taskBean.getIncomingNumber(),
                        taskBean.getExternalNumber(),
                        taskBean.getExternalCompany(),
                        taskBean.getExternalAssignee(),
                        taskBean.getDescription().replaceAll("\r\n", " "),
                        taskBean.getStartDate(),
                        taskBean.getDueDate()));
            }
            out.close();
        } catch (ParseException ex) {
            Logger.getLogger(TaskManagerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<TaskBean> getAllIncomingMailByYear(Integer currentYear) throws ParseException {
        return taskDao.getAllIncomingMailByYear(currentYear);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<TaskBean> getTaskByPrimaveraUid(String primaveraUid) {
        return taskDao.getTaskByPrimaveraUid("%" + primaveraUid + "%");
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<String> getAllTasksWithPrimaveraUid() {
        return taskDao.getAllTasksWithPrimaveraUid();
    }
}
