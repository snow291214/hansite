package ru.sgnhp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sgnhp.dao.IUserDao;
import ru.sgnhp.entity.WorkflowUserBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class UserDaoImpl extends GenericDaoHibernate<WorkflowUserBean, Long> implements IUserDao {

    public UserDaoImpl() {
        super(WorkflowUserBean.class);
    }

    @Override
    public List<WorkflowUserBean> getAll(){
        Map<String, Object> value = new HashMap<String, Object>();
        List<WorkflowUserBean> list = this.findByNamedQuery("WorkflowUserBean.findAll", value);
        for(WorkflowUserBean bean : list){
            bean.getReceivedWorkflows();
            bean.getAssignedWorkflows();
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public WorkflowUserBean getByLogin(String login) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("login", login);
        List<WorkflowUserBean> list = this.findByNamedQuery("WorkflowUserBean.findByLogin", value);
        for(WorkflowUserBean bean : list){
            bean.getReceivedWorkflows();
            bean.getAssignedWorkflows();
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public WorkflowUserBean getByEmail(String email) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("email", email);
        List<WorkflowUserBean> list = this.findByNamedQuery("WorkflowUserBean.findByEmail", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}

