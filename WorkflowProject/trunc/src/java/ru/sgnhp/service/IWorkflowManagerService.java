package ru.sgnhp.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import ru.sgnhp.domain.SearchTaskBean;
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

    List<WorkflowBean> getWorkflowsByDescription(Long userUid, SearchTaskBean searchTaskBean);

    List<WorkflowBean> getRecievedWorkflowsByUserUid(Long uid);

    List<WorkflowBean> getAssignedWorkflowsByUserUid(Long parentUid, Boolean completed);

    List<WorkflowBean> getCompletedWorkflowsByUserUid(Long parentUid);

    void updateWorkflow(WorkflowBean _workflow);

    void updateWorkflowState(WorkflowBean _workflow);

    //LinkedHashMap<Long, ArrayList<WorkflowUserBean>> getWorkflowMembersByWorkflowUid(Long workflowUid, LinkedHashMap roadmap);

    public ArrayList<WorkflowBean> getWorkflowMembersByWorkflowUid(Long workflowUid, Long workflowParentUid, ArrayList roadmap);

    //void sendmailRemind(WorkflowBean _workflow);

    void taskReminder();

    int getRecievedWorkflowsCountByUserUid(Long userUid);

    int getAssignedWorkflowsCountByUserUid(Long userUid);

    int getCompletedWorkflowsCountByUserUid(Long userUid);
}
