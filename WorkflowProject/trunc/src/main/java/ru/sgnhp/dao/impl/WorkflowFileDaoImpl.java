package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IWorkflowFileDao;
import ru.sgnhp.domain.WorkflowFileBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class WorkflowFileDaoImpl extends GenericDaoHibernate<WorkflowFileBean, Long> implements IWorkflowFileDao {

    public WorkflowFileDaoImpl() {
        super(WorkflowFileBean.class);
    }
}
