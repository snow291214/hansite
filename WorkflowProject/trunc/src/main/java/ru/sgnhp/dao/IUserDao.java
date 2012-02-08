package ru.sgnhp.dao;

import java.util.List;
import org.springframework.security.userdetails.UserDetails;
import ru.sgnhp.domain.WorkflowUserBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public interface IUserDao extends IGenericDao<WorkflowUserBean,Long>{

    WorkflowUserBean getByLogin(String login);

    WorkflowUserBean getByEmail(String email);

    @Override
    List<WorkflowUserBean> getAll();

    List<WorkflowUserBean> getAllEmailNotify();
    
    UserDetails loadUserByUsername(String username);
}
