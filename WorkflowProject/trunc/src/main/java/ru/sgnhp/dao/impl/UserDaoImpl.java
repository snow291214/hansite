package ru.sgnhp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import ru.sgnhp.dao.IUserDao;
import ru.sgnhp.domain.Role;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.UserLogin;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
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
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<WorkflowUserBean> getAllEmailNotify() {
        Map<String, Object> value = new HashMap<String, Object>();
        List<WorkflowUserBean> list = this.findByNamedQuery("WorkflowUserBean.findAllEmailNotify", value);
        for (WorkflowUserBean bean : list) {
            bean.getReceivedWorkflows();
            bean.getAssignedWorkflows();
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }
    
    @Override
    public WorkflowUserBean getByLogin(String login) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("login", login);
        List<WorkflowUserBean> list = this.findByNamedQuery("WorkflowUserBean.findByLogin", value);
        for (WorkflowUserBean bean : list) {
            bean.getReceivedWorkflows();
            bean.getAssignedWorkflows();
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public WorkflowUserBean getByEmail(String email) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("email", email);
        List<WorkflowUserBean> list = this.findByNamedQuery("WorkflowUserBean.findByEmail", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        WorkflowUserBean bean = this.getByLogin(username);
        //logger.info("First: " + username);
        UserLogin userLogin = new UserLogin();

        if (bean != null) {
            //logger.info("Second: " + bean.getLogin());
            userLogin.setUsername(username);
            userLogin.setAccountNonExpired(true);
            userLogin.setAccountNonLocked(true);
            userLogin.setCredentialsNonExpired(true);
            userLogin.setEnabled(true);
            userLogin.setAuthorities(new GrantedAuthority[]{new Role("ROLE_USER"),new Role(bean.getUserGroupBean().getName())});
            //logger.info("Third: " + userLogin.getAuthorities()[0].getAuthority()+", "+
            //        userLogin.getAuthorities()[1].getAuthority());
        }
        return userLogin;
    }
}
