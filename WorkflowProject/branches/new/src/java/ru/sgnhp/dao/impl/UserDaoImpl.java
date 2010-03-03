package ru.sgnhp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import ru.sgnhp.dao.IUserDao;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.UserLogin;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class UserDaoImpl extends GenericDaoHibernate<WorkflowUserBean, Long> implements IUserDao,UserDetailsService  {

    public UserDaoImpl() {
        super(WorkflowUserBean.class);
    }

    @Override
    public List<WorkflowUserBean> getAll() {
        Map<String, Object> value = new HashMap<String, Object>();
        List<WorkflowUserBean> list = this.findByNamedQuery("WorkflowUserBean.findAll", value);
        for (WorkflowUserBean bean : list) {
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
        for (WorkflowUserBean bean : list) {
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

    public UserDetails loadUserByUsername(String username) {
        WorkflowUserBean bean = this.getByLogin(username);
        UserLogin userLogin = new UserLogin();
        if (bean != null) {
            userLogin.setUsername(bean.getLogin());
        }
        return userLogin;
    }
}
