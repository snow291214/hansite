package ru.sgnhp.service.impl;

import java.net.URL;
import java.util.List;
import org.apache.commons.mail.HtmlEmail;
import ru.sgnhp.dao.IWorkflowDao;
import ru.sgnhp.domain.WorkflowBean;
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
    private static String mailHostName;
    private static String fromAddress;
    private static String fromName;
    private static IUserManagerService userManagerService;

    public void assignTaskToUser(WorkflowBean _workflow) {
        HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName(mailHostName);
            email.addTo(_workflow.getReceiver().getEmail(),
                    _workflow.getReceiver().getLastName() +
                    _workflow.getReceiver().getFirstName() +
                    _workflow.getReceiver().getMiddleName());
            email.setFrom(fromAddress, fromName, "utf-8");
            email.setSubject("Была создана новая задача");
            
            // embed the image and get the content id
            URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
            String cid = email.embed(url, "Apache logo");
            email.setCharset("utf-8");
            // set the html message
            email.setHtmlMsg("<html><h1>Задача № " +
                    _workflow.getTask().getInternalNumber()+"</h1>" +
                    "<p>Задачу назначил: "+_workflow.getAssignee().getFirstName()+" "
                    +_workflow.getAssignee().getMiddleName()+" "
                    +_workflow.getAssignee().getLastName()+"</p>"
                    +"<p>Резолюция к задаче: "+_workflow.getDescription()
                    +"</p>"
                    +"</html>");
            // set the alternative message
            email.setTextMsg("Your email client does not support HTML messages");

            // send the email
            email.send();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        workflowDao.saveWorkflow(_workflow);
    }

    public void setWorkflowDao(IWorkflowDao workflowDao) {
        this.workflowDao = workflowDao;
    }

    public List<WorkflowBean> getRecievedWorkflowsByUid(Long uid) {
        return workflowDao.getRecievedWorkflowsByUserUid(uid);
    }

    public List<WorkflowBean> getAssignedWorkflowsByParentUid(Long parentUid) {
        return workflowDao.getAssignedWorkflowsByUserUid(parentUid);
    }

    public WorkflowBean getWorkflowByUid(Long workflowUid) {
        WorkflowBean wf = workflowDao.getWorkflowByUid(workflowUid);
        wf.setAssignee(userManagerService.getUserByUid(wf.getParentUserUid()));
        wf.setReceiver(userManagerService.getUserByUid(wf.getUserUid()));
        return wf;
    }

    public void updateWorkflow(WorkflowBean _workflow) {
        workflowDao.updateWorkflow(_workflow);
    }

    public void setMailHostName(String mailHostName) {
        WorkflowManagerServiceImpl.mailHostName = mailHostName;
    }

    public void setFromAddress(String fromAddress) {
        WorkflowManagerServiceImpl.fromAddress = fromAddress;
    }

    public void setFromName(String fromName) {
        WorkflowManagerServiceImpl.fromName = fromName;
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        WorkflowManagerServiceImpl.userManagerService = userManagerService;
    }
}
