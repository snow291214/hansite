package ru.sgnhp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sgnhp.dao.ITaskDao;
import ru.sgnhp.domain.TaskBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class TaskDaoImpl extends GenericDaoHibernate<TaskBean, Long> implements ITaskDao {

    public TaskDaoImpl() {
        super(TaskBean.class);
    }

    public TaskBean getTaskByInternalNumber(int number) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("internalNumber", number);
        List<TaskBean> list = this.findByNamedQuery("TaskBean.findByInternalNumber", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public TaskBean getTaskByExternalNumber(String number) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("externalNumber", number);
        List<TaskBean> list = this.findByNamedQuery("TaskBean.findByExternalNumber", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public TaskBean getTaskByIncomingNumber(int number) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("incomingNumber", number);
        List<TaskBean> list = this.findByNamedQuery("TaskBean.findByIncomingNumber", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public List<TaskBean> getTasksByExternalAssignee(String externalAssignee) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("externalAssignee", externalAssignee);
        List<TaskBean> list = this.findByNamedQuery("TaskBean.findByExternalAssignee", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public List<TaskBean> getTasksByDescription(String description) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("description", description);
        List<TaskBean> list = this.findByNamedQuery("TaskBean.findByDescription", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public int getNewInternalNumber() {
        List list = getSession().createQuery("SELECT Max(t.internalNumber) FROM TaskBean t").list();
        if (list.get(0) instanceof Integer) {
            return (Integer) list.get(0);
        }
        return -1;
    }

    public int getNewIncomingNumber() {
        List list = getSession().createQuery("SELECT Max(t.incomingNumber) FROM TaskBean t").list();
        if (list.get(0) instanceof Integer) {
            return (Integer) list.get(0);
        }
        return -1;
    }
}
