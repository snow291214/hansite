/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.dao;

import java.util.List;
import ru.sgnhp.domain.Workflow;
import ru.sgnhp.domain.WorkflowUser;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IUserDao {

    void save(WorkflowUser user);

    void update(WorkflowUser user);

    WorkflowUser getByUid(Long userUid);

    WorkflowUser getByLogin(String login);

    WorkflowUser getByEmail(String email);

    void delete(WorkflowUser user);

    void deleteByUid(Long userUid);

    List<WorkflowUser> getAllUsers();

    List<WorkflowUser> getNormalisedUsers();
}
