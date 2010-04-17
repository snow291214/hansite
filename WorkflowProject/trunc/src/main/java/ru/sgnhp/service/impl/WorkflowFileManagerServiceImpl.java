package ru.sgnhp.service.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.IWorkflowFileDao;
import ru.sgnhp.domain.WorkflowFileBean;
import ru.sgnhp.service.IWorkflowFileManagerService;

/*
 ******
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class WorkflowFileManagerServiceImpl extends GenericServiceImpl<WorkflowFileBean, Long>
        implements IWorkflowFileManagerService {

    private IWorkflowFileDao workflowFileDao;

    public WorkflowFileManagerServiceImpl(IGenericDao<WorkflowFileBean, Long> genericDao) {
        super(genericDao);
    }

    public IWorkflowFileDao getWorkflowFileDao() {
        return workflowFileDao;
    }

    public void setWorkflowFileDao(IWorkflowFileDao workflowFileDao) {
        this.workflowFileDao = workflowFileDao;
    }
}
