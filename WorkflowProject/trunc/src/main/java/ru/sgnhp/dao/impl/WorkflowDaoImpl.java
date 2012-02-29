package ru.sgnhp.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sgnhp.dao.IWorkflowDao;
import ru.sgnhp.domain.StateBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.dto.WorkflowBeanDto;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class WorkflowDaoImpl extends GenericDaoHibernate<WorkflowBean, Long> implements IWorkflowDao {

    public WorkflowDaoImpl() {
        super(WorkflowBean.class);
    }

    public List<WorkflowBean> getWorkflowByParentUid(Long parentUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("parentUid", parentUid);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findByParentUid", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<WorkflowBean> getRecievedWorkflowsByUserUid(Long userUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("userUid", userUid);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findRecievedByUserUid", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<WorkflowBean> getAssignedWorkflowsByUserUid(Long userUid, Boolean completed) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("userUid", userUid);
        List<WorkflowBean> list = null;
        if (!completed) {
            list = this.findByNamedQuery("WorkflowBean.findAssignedByUserUid", value);
        } else {
            list = this.findByNamedQuery("WorkflowBean.findAssignedAndCompletedByUserUid", value);
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<WorkflowBean> getCompletedWorkflowsByUserUid(Long userUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("userUid", userUid);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findCompletedByUserUid", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<WorkflowBean> getWorkflowsByTaskUid(Long taskUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("taskUid", taskUid);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findByTaskUid", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<WorkflowBean> getWorkflowsByDescription(Long userUid, String description) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("userUid", userUid);
        value.put("description", description);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findByDescription", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<WorkflowBean> getByAssignDate(Date assignedDate) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("assignDate", assignedDate);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findByAssignDate", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<WorkflowBean> getByFinishDate(Date finishDate) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("finishDate", finishDate);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findByFinishDate", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<WorkflowBean> getRecievedWorkflows() {
        Map<String, Object> value = new HashMap<String, Object>();
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findReceived", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

//    public void updateWorkflow(WorkflowBean workflowBean) {
//        WorkflowBean bean = this.get(workflowBean.getUid());
//        bean.setState(workflowBean.getState());
//        super.save(bean);
//    }
    @Override
    public WorkflowBeanDto updateWorkflowState(WorkflowBeanDto beanDto, StateBean stateBean) {
        WorkflowBean workflowBean = this.get(beanDto.getUid());
        workflowBean.setState(stateBean);
        workflowBean.setWorkflowNote(beanDto.getWorkflowNote());
        workflowBean.setFinishDate(beanDto.getFinishDate());
        workflowBean = super.save(workflowBean);

        beanDto.setUid(workflowBean.getUid());
        beanDto.setParentUid(workflowBean.getParentUid());
        beanDto.setTaskBean(workflowBean.getTaskBean());
        beanDto.setAssignee(workflowBean.getAssignee());
        beanDto.setReceiver(workflowBean.getReceiver());
        beanDto.setAssignDate(workflowBean.getAssignDate());
        beanDto.setFinishDate(workflowBean.getFinishDate());
        beanDto.setStateBean(stateBean);

        return beanDto;
    }

    @Override
    public List<WorkflowBean> getAllUncompletedByParentUserUid(Long parentUserUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("parentUserUid", parentUserUid);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findAllUncompletedByParentUserUid", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<WorkflowBean> getAllUncompletedByParentUserUidEx(Long parentUserUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("parentUserUid", parentUserUid);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findAllUncompletedByParentUserUidEx", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }
    
    @Override
    public List<WorkflowBean> getWorkflowsByPeriodOfDate(Long parentUserUid, Long userUid, Date startDate, Date finishDate) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("parentUserUid", parentUserUid);
        value.put("userUid", userUid);
        value.put("startDate", startDate);
        value.put("finishDate", finishDate);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findByPeriodOfDate", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<WorkflowBean> getWorkflowsByUserUidAndStateUids(Long userUid, Long[] stateUids) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("userUid", userUid);
        value.put("stateUids", stateUids);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findByUserUidAndStateUids", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public boolean isTaskAssignedToUser(Long taskUid, Long userUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("userUid", userUid);
        value.put("taskUid", taskUid);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findByTaskUidAndUserUid", value);
        if (list == null || list.isEmpty()) {
            return false;
        }
        return true;
    }
}
