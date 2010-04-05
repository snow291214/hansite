package ru.sgnhp.services;

import java.util.List;
import ru.sgnhp.entity.WorkflowUserBean;

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

    List<WorkflowUserBean> getAll();
}