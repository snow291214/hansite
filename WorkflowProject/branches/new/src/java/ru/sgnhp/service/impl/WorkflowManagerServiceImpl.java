package ru.sgnhp.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.DateUtils;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.IWorkflowDao;
import ru.sgnhp.dto.SearchTaskDto;
import ru.sgnhp.domain.StateBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.WorkflowBeanDto;
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

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
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

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void updateWorkflow(WorkflowBean _workflow) {
        if (_workflow.getState().getStateUid() == 3) {
            _workflow.setFinishDate(DateUtils.nowDate());
        }
        workflowDao.updateWorkflow(_workflow);
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
        List<WorkflowBean> workflowBeans = this.getWorkflowByParentUid(workflowUid);
        if (workflowBeans != null) {
            for (WorkflowBean workflowBean : workflowBeans) {
                roadmap.add(workflowBean);
                workflowUid = workflowBean.getUid();
                stepDown(workflowUid, roadmap);
            }
        }
        return roadmap;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<WorkflowBean> getWorkflowsByDescription(Long userUid, SearchTaskDto searchTaskBean) {
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

    public List<WorkflowBean> getAllUncompletedByParentUserUid(Long parentUserUid) {
        return workflowDao.getAllUncompletedByParentUserUid(parentUserUid);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public WorkflowBeanDto updateWorkflowState(WorkflowBeanDto beanDto, StateBean stateBean) {
        if (stateBean.getStateUid() == 3) {
            beanDto.setFinishDate(DateUtils.nowDate());
        }
        beanDto = workflowDao.updateWorkflowState(beanDto, stateBean);
        WorkflowBean workflowBean = this.getWorkflowByUid(beanDto.getUid());
        mailService.sendmailChangeState(workflowBean);
        return beanDto;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void taskReminder() {
        List<WorkflowUserBean> users = userManagerService.getAll();
        for (WorkflowUserBean user : users) {
            List<WorkflowBean> workflows = this.getRecievedWorkflowsByUserUid(user.getUid());
            if (workflows != null) {
                mailService.sendmailSheduler((ArrayList) workflows);
            }
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void taskReport() {
        List<WorkflowBean> uncompletedWorkflows = this.getAllUncompletedByParentUserUid(75L);
        ArrayList<WorkflowBean> roadmapsList = new ArrayList<WorkflowBean>();
        for (WorkflowBean workflowBean : uncompletedWorkflows) {
            ArrayList<WorkflowBean> roadmap = this.getWorkflowMembersByWorkflowUid(workflowBean.getUid(),
                    workflowBean.getParentUid(), new ArrayList());
            roadmapsList.addAll(roadmap);
        }
        if (roadmapsList.size() > 0) {
            mailService.sendmailReport(roadmapsList);
        }
//        List<WorkflowUserBean> users = userManagerService.getAll();
//        for (WorkflowUserBean user : users) {
//            List<WorkflowBean> workflows = this.getAllUncompletedByParentUserUid(user.getUid());
//            ArrayList<ArrayList<WorkflowBean>> workflowBeans = new ArrayList<ArrayList<WorkflowBean>>();
//            if (workflows != null) {
//                for (WorkflowBean workflowBean : workflows) {
//                    ArrayList arrayList = this.getWorkflowMembersByWorkflowUid(workflowBean.getUid(), workflowBean.getParentUid(), new ArrayList());
//                    if (arrayList == null) {
//                        workflowBeans.add(arrayList);
//                    } else {
//                        workflowBeans.addAll(arrayList);
//                    }
//                }
//            }
//            if (workflowBeans.size() > 0) {
//                mailService.sendmailReport(workflowBeans);
//            }
//        }
    }
}