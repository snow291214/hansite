package ru.sgnhp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sgnhp.dao.IWorkflowDao;
import ru.sgnhp.domain.WorkflowBean;

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

    public WorkflowBean getWorkflowByParentUid(Long parentUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("parentUid", parentUid);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findByParentUid", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public List<WorkflowBean> getRecievedWorkflowsByUserUid(Long userUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("userUid", userUid);
        List<WorkflowBean> list = this.findByNamedQuery("WorkflowBean.findByUserUid", value);
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
        }else{
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getCompletedWorkflowsCountByUserUid(Long userUid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<WorkflowBean> getWorkflowsByTaskUid(Long taskUid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<WorkflowBean> getWorkflowsByDescription(Long userUid, String description) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
