package ru.sgnhp.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IWorkflowManagerService {

    void assignTaskToUser(WorkflowBean _workflow);

    WorkflowBean getWorkflowByUid(Long workflowUid);

    List<WorkflowBean> getRecievedWorkflowsByUserUid(Long uid);

    List<WorkflowBean> getAssignedWorkflowsByUserUid(Long parentUid);

    List<WorkflowBean> getCompletedWorkflowsByUserUid(Long parentUid);

    void updateWorkflow(WorkflowBean _workflow);

    void updateWorkflowState(WorkflowBean _workflow);

    LinkedHashMap<Long, ArrayList<WorkflowUserBean>> getWorkflowMembersByWorkflowUid(Long workflowUid, LinkedHashMap roadmap);

    public void sendmailRemind(WorkflowBean _workflow);

}
