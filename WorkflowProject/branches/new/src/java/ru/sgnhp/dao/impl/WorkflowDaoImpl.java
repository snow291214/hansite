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
 * @company "Salavatgazoneftehimproekt" Ltd
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
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public List<WorkflowBean> getRecievedWorkflowsByUserUid(Long userUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("userUid", userUid);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findRecievedByUserUid", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public int getRecievedWorkflowsCountByUserUid(Long userUid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<WorkflowBean> getAssignedWorkflowsByUserUid(Long userUid, Boolean completed) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("userUid", userUid);
        List<WorkflowBean> list = null;
        if (!completed) {
            list = this.findByNamedQuery("WorkflowBean.findAssignedByUserUid", value);
        } else {
            list = this.findByNamedQuery("WorkflowBean.findAssignedAndCompletedByUserUid", value);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public int getAssignedWorkflowsCountByUserUid(Long userUid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<WorkflowBean> getCompletedWorkflowsByUserUid(Long userUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("userUid", userUid);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findCompletedByUserUid", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public int getCompletedWorkflowsCountByUserUid(Long userUid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<WorkflowBean> getWorkflowsByTaskUid(Long taskUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("taskUid", taskUid);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findByTaskUid", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public List<WorkflowBean> getWorkflowsByDescription(Long userUid, String description) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("userUid", userUid);
        value.put("description", description);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findByDescription", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public List<WorkflowBean> getByAssignDate(Date assignedDate) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("assignDate", assignedDate);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findByAssignDate", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public List<WorkflowBean> getByFinishDate(Date finishDate) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("finishDate", finishDate);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findByFinishDate", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public List<WorkflowBean> getRecievedWorkflows() {
        Map<String, Object> value = new HashMap<String, Object>();
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findReceived", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public void updateWorkflow(WorkflowBean workflowBean) {
        WorkflowBean bean = this.get(workflowBean.getUid());
        bean.setState(workflowBean.getState());
        super.save(bean);
    }

    public WorkflowBeanDto updateWorkflowState(WorkflowBeanDto beanDto, StateBean stateBean){
        WorkflowBean workflowBean = this.get(beanDto.getUid());
        workflowBean.setState(stateBean);
        workflowBean = super.save(workflowBean);
        beanDto.setUid(workflowBean.getUid());
        beanDto.setParentUid(workflowBean.getParentUid());
        beanDto.setTaskBean(workflowBean.getTaskBean());
        beanDto.setAssignee(workflowBean.getAssignee());
        beanDto.setReceiver(workflowBean.getReceiver());
        beanDto.setAssignDate(workflowBean.getAssignDate());
        beanDto.setFinishDate(workflowBean.getFinishDate());
        beanDto.setStateBean(stateBean);
        beanDto.setWorkflowNote("");
        return beanDto;
    }
}
