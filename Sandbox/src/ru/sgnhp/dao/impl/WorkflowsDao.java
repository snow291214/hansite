package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IWorkflowsDao;
import ru.sgnhp.entity.Workflows;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class WorkflowsDao extends GenericDaoHibernate<Workflows, Long> implements IWorkflowsDao {

    public WorkflowsDao(){
        super(Workflows.class);
    }
}
