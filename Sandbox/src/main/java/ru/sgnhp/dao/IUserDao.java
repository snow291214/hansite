package ru.sgnhp.dao;

import java.util.List;
import ru.sgnhp.entity.WorkflowUserBean;

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
}

