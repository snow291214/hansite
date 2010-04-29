package ru.sgnhp.service;

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
public interface IUserManagerService extends IGenericService<WorkflowUserBean, Long> {

    WorkflowUserBean getUserByLogin(String login);

    WorkflowUserBean getUserByEmail(String email);

    @Override
    List<WorkflowUserBean> getAll();

    public UserDetails loadUserByUsername(String username);
}
