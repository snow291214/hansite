package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IWorkflowFileDao;
import ru.sgnhp.domain.WorkflowFileBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class WorkflowFileDaoImpl extends GenericDaoHibernate<WorkflowFileBean, Long> implements IWorkflowFileDao {

    public WorkflowFileDaoImpl() {
        super(WorkflowFileBean.class);
    }
}
