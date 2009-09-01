package ru.sgnhp.service.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.sgnhp.dao.IWorkflowDao;
import ru.sgnhp.domain.Workflow;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class WorkflowManagerServiceImpl implements IWorkflowManagerService{
    private IWorkflowDao workflowDao;
    protected final Log logger = LogFactory.getLog(getClass());

    public void assignTaskToUser(Workflow _workflow) {
        workflowDao.saveWorkflow(_workflow);
    }

    public void setWorkflowDao(IWorkflowDao workflowDao) {
        this.workflowDao = workflowDao;
    }

    public List<Workflow> getRecievedWorkflowsByUid(Long uid) {
        return workflowDao.getRecievedWorkflowsByUserUid(uid);
    }

    public List<Workflow> getAssignedWorkflowsByParentUid(Long parentUid) {
        return workflowDao.getAssignedWorkflowsByUserUid(parentUid);
    }

    public Workflow getWorkflowByUid(Long workflowUid) {
       return workflowDao.getWorkflowByUid(workflowUid);
    }

    public void updateWorkflow(Workflow _workflow) {
        workflowDao.updateWorkflow(_workflow);
    }

}
