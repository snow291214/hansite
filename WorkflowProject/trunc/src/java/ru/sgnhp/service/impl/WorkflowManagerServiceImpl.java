package ru.sgnhp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import ru.sgnhp.dao.IWorkflowDao;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
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

    protected void sendmailAssign(WorkflowBean _workflow) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
            message.setFrom(address);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(_workflow.getReceiver().getEmail()));
            message.setSubject("Была создана новая задача", "utf-8");

            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();

            htmlPart.setContent("<html><body><h2>Задача № " +
                    _workflow.getTask().getInternalNumber() + "</h2>" +
                    "<p style=\"font-family:Arial;font-size:12px;\">Задачу назначил: " + _workflow.getAssignee().getFirstName() +
                    " " + _workflow.getAssignee().getMiddleName() + " " +
                    _workflow.getAssignee().getLastName() + "</p>" +
                    "<p style=\"font-family:Arial;font-size:12px;\">Резолюция к задаче: " + _workflow.getDescription() +
                    "</p><a href=\"http://sgnhp.snos.ru:8080/Workflow\">Просмотреть задачу</a></body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void assignTaskToUser(WorkflowBean _workflow) {
        sendmailAssign(_workflow);
        workflowDao.saveWorkflow(_workflow);
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

    public List<WorkflowBean> getAssignedWorkflowsByUserUid(Long parentUid) {
        List<WorkflowBean> wfs = workflowDao.getAssignedWorkflowsByUserUid(parentUid);
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

    public HashMap<Long, ArrayList<WorkflowUserBean>> getWorkflowMembersByWorkflowUid(Long workflowUid, HashMap roadmap) {
        WorkflowBean workflowBean = this.getWorkflowByUid(workflowUid);
        ArrayList<WorkflowUserBean> members = new ArrayList<WorkflowUserBean>();
        members.add(workflowBean.getAssignee());
        members.add(workflowBean.getReceiver());
        roadmap.put(workflowBean.getUid(), members);
        if (workflowBean.getParentUid() != -1) {
            workflowUid = workflowBean.getParentUid();
            this.getWorkflowMembersByWorkflowUid(workflowUid, roadmap);
        }
        return roadmap;
    }

    public void updateWorkflowState(WorkflowBean _workflow) {
        workflowDao.updateWorkflowState(_workflow);
    }
}
