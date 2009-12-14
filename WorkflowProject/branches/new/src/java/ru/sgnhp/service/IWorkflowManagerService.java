package ru.sgnhp.service;

import java.util.ArrayList;
import java.util.List;
import ru.sgnhp.domain.SearchTaskBean;
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
public interface IWorkflowManagerService extends IGenericService<WorkflowBean,Long>{

    WorkflowBean assignTaskToUser(WorkflowBean _workflow);

    WorkflowBean getWorkflowByUid(Long workflowUid);

    List<WorkflowBean> getRecievedWorkflowsByUserUid(Long uid);

    List<WorkflowBean> getRecievedWorkflows();

    List<WorkflowBean> getAssignedWorkflowsByUserUid(Long parentUid, Boolean completed);

    List<WorkflowBean> getCompletedWorkflowsByUserUid(Long parentUid);

    void updateWorkflow(WorkflowBean _workflow);

    WorkflowBeanDto updateWorkflowState(WorkflowBeanDto beanDto, StateBean stateBean);

    public ArrayList<WorkflowBean> getWorkflowMembersByWorkflowUid(Long workflowUid, Long workflowParentUid, ArrayList roadmap);

    void taskReminder();

    List<WorkflowBean> getWorkflowsByDescription(Long userUid, SearchTaskBean searchTaskBean);

    List<WorkflowBean> getWorkflowsByTaskUid(Long taskUid);

    List<WorkflowBean> getWorkflowByParentUid(Long workflowUid);
    
}
