package ru.sgnhp.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.DateUtils;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.IWorkflowDao;
import ru.sgnhp.domain.SearchTaskBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IMailService;
import ru.sgnhp.service.IUserManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
@Transactional(readOnly = true)
public class WorkflowManagerServiceImpl extends GenericServiceImpl<WorkflowBean, Long> implements IWorkflowManagerService {

    private IWorkflowDao workflowDao;
    private static IUserManagerService userManagerService;
    private IMailService mailService;

    public WorkflowManagerServiceImpl(IGenericDao<WorkflowBean, Long> genericDao) {
        super(genericDao);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void taskReminder() {
        List<WorkflowUserBean> users = userManagerService.getAll();
        for (WorkflowUserBean user : users) {
            List<WorkflowBean> workflows = this.getRecievedWorkflowsByUserUid(user.getUid());
            if (!workflows.isEmpty()) {
                mailService.sendmailSheduler((ArrayList) workflows);
            }
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public WorkflowBean assignTaskToUser(WorkflowBean wf) {
        wf = workflowDao.save(wf);
        mailService.sendmailAssign(wf);
        return wf;
    }

    public void setWorkflowDao(IWorkflowDao workflowDao) {
        this.workflowDao = workflowDao;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<WorkflowBean> getRecievedWorkflowsByUserUid(Long uid) {
        return workflowDao.getRecievedWorkflowsByUserUid(uid);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<WorkflowBean> getAssignedWorkflowsByUserUid(Long parentUid, Boolean completed) {
        return workflowDao.getAssignedWorkflowsByUserUid(parentUid, completed);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<WorkflowBean> getCompletedWorkflowsByUserUid(Long uid) {
        return workflowDao.getCompletedWorkflowsByUserUid(uid);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public WorkflowBean getWorkflowByUid(Long workflowUid) {
        return workflowDao.get(workflowUid);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<WorkflowBean> getWorkflowByParentUid(Long workflowUid) {
        return workflowDao.getWorkflowByParentUid(workflowUid);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateWorkflow(WorkflowBean _workflow) {
        if (_workflow.getState().getStateUid() == 3) {
            _workflow.setFinishDate(DateUtils.nowDate());
        }
        workflowDao.save(_workflow);
        mailService.sendmailChangeState(_workflow);
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        WorkflowManagerServiceImpl.userManagerService = userManagerService;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public ArrayList<WorkflowBean> getWorkflowMembersByWorkflowUid(Long workflowUid, Long workflowParentUid, ArrayList roadmap) {
        ArrayList<WorkflowBean> up = stepDown(workflowUid, new ArrayList());
        Collections.reverse(up);
        if (workflowParentUid != -1) {
            roadmap = stepUp(workflowParentUid, roadmap);
        }
        up.addAll(roadmap);
        return up;
    }

    public ArrayList<WorkflowBean> stepUp(Long workflowUid, ArrayList roadmap) {
        WorkflowBean workflowBean = this.getWorkflowByUid(workflowUid);
        roadmap.add(workflowBean);
        if (workflowBean.getParentUid() != -1) {
            workflowUid = workflowBean.getParentUid();
            stepUp(workflowUid, roadmap);
        }
        return roadmap;
    }

    private ArrayList<WorkflowBean> stepDown(Long workflowUid, ArrayList roadmap) {
        WorkflowBean workflowBean = this.getWorkflowByParentUid(workflowUid).get(0);
        if (workflowBean != null) {
            roadmap.add(workflowBean);
            workflowUid = workflowBean.getUid();
            stepDown(workflowUid, roadmap);
        }
        return roadmap;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<WorkflowBean> getWorkflowsByDescription(Long userUid, SearchTaskBean searchTaskBean) {
        return workflowDao.getWorkflowsByDescription(userUid, searchTaskBean.getTaskDescription());
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<WorkflowBean> getWorkflowsByTaskUid(Long taskUid) {
        return workflowDao.getWorkflowsByTaskUid(taskUid);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<WorkflowBean> getRecievedWorkflows() {
        return workflowDao.getRecievedWorkflows();
    }
}
