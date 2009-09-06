/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.dao;

import java.util.List;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IUserDao {

    void save(WorkflowUserBean user);

    void update(WorkflowUserBean user);

    WorkflowUserBean getByUid(Long userUid);

    WorkflowUserBean getByLogin(String login);

    WorkflowUserBean getByEmail(String email);

    void delete(WorkflowUserBean user);

    void deleteByUid(Long userUid);

    List<WorkflowUserBean> getAllUsers();

    List<WorkflowUserBean> getNormalisedUsers();
}
