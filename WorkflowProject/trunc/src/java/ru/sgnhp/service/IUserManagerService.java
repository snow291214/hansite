package ru.sgnhp.service;

import java.util.List;
import ru.sgnhp.domain.WorkflowUser;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IUserManagerService {

    void registerNewUser(WorkflowUser siteUser);

    void updateUser(WorkflowUser siteUser);

    void deleteUser(Long userUid);

    WorkflowUser getUserByLogin(String login);

    WorkflowUser getUserByEmail(String email);

    WorkflowUser getUserByUid(Long userUid);

    List<WorkflowUser> getAllUsers();

    List<WorkflowUser> getAllNormalizedUsers();
}
