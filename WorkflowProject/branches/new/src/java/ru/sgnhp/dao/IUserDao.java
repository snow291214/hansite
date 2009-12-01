/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.dao;

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
}
