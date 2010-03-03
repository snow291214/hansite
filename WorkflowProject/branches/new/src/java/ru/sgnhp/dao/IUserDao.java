package ru.sgnhp.dao;

import java.util.List;
import org.springframework.security.userdetails.UserDetails;
import ru.sgnhp.domain.WorkflowUserBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IUserDao extends IGenericDao<WorkflowUserBean,Long>{

    WorkflowUserBean getByLogin(String login);

    WorkflowUserBean getByEmail(String email);

    List<WorkflowUserBean> getAll();

    public UserDetails loadUserByUsername(String username);
}
