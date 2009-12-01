package ru.sgnhp.service;

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
}
