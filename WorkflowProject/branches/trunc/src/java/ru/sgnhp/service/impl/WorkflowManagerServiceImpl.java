package ru.sgnhp.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ru.sgnhp.dao.IWorkflowDao;
import ru.sgnhp.domain.SearchTaskBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IMailService;
import ru.sgnhp.service.IStateManagerService;
import ru.sgnhp.service.IUserManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class WorkflowManagerServiceImpl implements IWorkflowManagerService {

    private IWorkflowDao workflowDao;
//    private static String mailHostName;
//    private static String fromAddress;
//    private static String fromName;
    private static IUserManagerService userManagerService;
    private IMailService mailService;
    private IStateManagerService stateManagerService;

    public void taskReminder() {
        List<WorkflowUserBean> users = userManagerService.getAllNormalizedUsers();
        for (WorkflowUserBean user : users) {
            List<WorkflowBean> workflows = this.getRecievedWorkflowsByUserUid(user.getUid());
            if (!workflows.isEmpty()) {
                mailService.sendmailSheduler((ArrayList) workflows);
            }
        }
    }

    public void assignTaskToUser(WorkflowBean wf) {
        wf.setAssignee(userManagerService.getUserByUid(wf.getParentUserUid()));
        wf.setReceiver(userManagerService.getUserByUid(wf.getUserUid()));
        mailService.sendmailAssign(wf);
        workflowDao.saveWorkflow(wf);
    }

    public void setWorkflowDao(IWorkflowDao workflowDao) {
        this.workflowDao = workflowDao;
    }
    /*
     *  Получаем список Workflows по Uid
     *  и проставляем туда пользователей
     */

    public List<WorkflowBean> getRecievedWorkflowsByUserUid(Long uid) {
        List<WorkflowBean> wfs = workflowDao.getRecievedWorkflowsByUserUid(uid);
        for (WorkflowBean wf : wfs) {
            wf.setAssignee(userManagerService.getUserByUid(wf.getParentUserUid()));
            wf.setReceiver(userManagerService.getUserByUid(wf.getUserUid()));
        }
        return wfs;
    }

    public List<WorkflowBean> getAssignedWorkflowsByUserUid(Long parentUid, Boolean completed) {
        List<WorkflowBean> wfs = workflowDao.getAssignedWorkflowsByUserUid(parentUid, completed);
        for (WorkflowBean wf : wfs) {
            wf.setAssignee(userManagerService.getUserByUid(wf.getParentUserUid()));
            wf.setReceiver(userManagerService.getUserByUid(wf.getUserUid()));
        }
        return wfs;
    }

    public List<WorkflowBean> getCompletedWorkflowsByUserUid(Long uid) {
        List<WorkflowBean> wfs = workflowDao.getCompletedWorkflowsByUserUid(uid);
        for (WorkflowBean wf : wfs) {
            wf.setAssignee(userManagerService.getUserByUid(wf.getParentUserUid()));
            wf.setReceiver(userManagerService.getUserByUid(wf.getUserUid()));
        }
        return wfs;
    }

    public WorkflowBean getWorkflowByUid(Long workflowUid) {
        WorkflowBean wf = workflowDao.getWorkflowByUid(workflowUid);
        wf.setAssignee(userManagerService.getUserByUid(wf.getParentUserUid()));
        wf.setReceiver(userManagerService.getUserByUid(wf.getUserUid()));
        return wf;
    }

    public WorkflowBean getWorkflowByParentUid(Long workflowUid) {
        WorkflowBean wf = workflowDao.getWorkflowByParentUid(workflowUid);
        if (wf != null) {
            wf.setAssignee(userManagerService.getUserByUid(wf.getParentUserUid()));
            wf.setReceiver(userManagerService.getUserByUid(wf.getUserUid()));
        }
        return wf;
    }

    public void updateWorkflow(WorkflowBean _workflow) {
        workflowDao.updateWorkflow(_workflow);
    }

//    public void setMailHostName(String mailHostName) {
//        WorkflowManagerServiceImpl.mailHostName = mailHostName;
//    }
//
//    public void setFromAddress(String fromAddress) {
//        WorkflowManagerServiceImpl.fromAddress = fromAddress;
//    }
//
//    public void setFromName(String fromName) {
//        WorkflowManagerServiceImpl.fromName = fromName;
//    }
    public void setUserManagerService(IUserManagerService userManagerService) {
        WorkflowManagerServiceImpl.userManagerService = userManagerService;
    }

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
        WorkflowBean workflowBean = this.getWorkflowByParentUid(workflowUid);
        if (workflowBean != null) {
            roadmap.add(workflowBean);
            workflowUid = workflowBean.getUid();
            stepDown(workflowUid, roadmap);
        }
        return roadmap;
    }

    public void updateWorkflowState(WorkflowBean _workflow) {
        workflowDao.updateWorkflowState(_workflow);
        mailService.sendmailChangeState(_workflow);
    }

    public void setStateManagerService(IStateManagerService stateManagerService) {
        this.stateManagerService = stateManagerService;
    }

    public int getRecievedWorkflowsCountByUserUid(Long userUid) {
        return workflowDao.getRecievedWorkflowsCountByUserUid(userUid);
    }

    public int getAssignedWorkflowsCountByUserUid(Long userUid) {
        return workflowDao.getAssignedWorkflowsCountByUserUid(userUid);
    }

    public int getCompletedWorkflowsCountByUserUid(Long userUid) {
        return workflowDao.getCompletedWorkflowsCountByUserUid(userUid);
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public List<WorkflowBean> getWorkflowsByDescription(Long userUid, SearchTaskBean searchTaskBean) {
        List<WorkflowBean> wfs = workflowDao.getWorkflowsByDescription(userUid, searchTaskBean.getTaskDescription());
//        for (WorkflowBean wf : wfs) {
//            wf.setAssignee(userManagerService.getUserByUid(wf.getParentUserUid()));
//            wf.setReceiver(userManagerService.getUserByUid(wf.getUserUid()));
//        }
        return wfs;
    }

    public List<WorkflowBean> getWorkflowsByTaskUid(Long taskUid) {
        List<WorkflowBean> wfs = workflowDao.getWorkflowsByTaskUid(taskUid);
//        for (WorkflowBean wf : wfs) {
//            wf.setAssignee(userManagerService.getUserByUid(wf.getParentUserUid()));
//            wf.setReceiver(userManagerService.getUserByUid(wf.getUserUid()));
//        }
        return wfs;
    }
}
