package ru.sgnhp.service;

import java.util.List;
import ru.sgnhp.domain.WorkflowUserBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IUserManagerService {

    void registerNewUser(WorkflowUserBean siteUser);

    void updateUser(WorkflowUserBean siteUser);

    void deleteUser(Long userUid);

    WorkflowUserBean getUserByLogin(String login);

    WorkflowUserBean getUserByEmail(String email);

    WorkflowUserBean getUserByUid(Long userUid);

    List<WorkflowUserBean> getAllUsers();

    List<WorkflowUserBean> getAllNormalizedUsers();
}
