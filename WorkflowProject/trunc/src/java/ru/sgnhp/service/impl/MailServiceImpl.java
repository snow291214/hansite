package ru.sgnhp.service.impl;

import java.util.ArrayList;
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
import ru.sgnhp.domain.StateBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.service.IMailService;
import ru.sgnhp.service.IStateManagerService;
import ru.sgnhp.service.IUserManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class MailServiceImpl implements IMailService {

    private String mailHostName;
    private String fromAddress;
    private String fromName;
    private IUserManagerService userManagerService;
    private IStateManagerService stateManagerService;

    public void sendmailAssign(WorkflowBean _workflow) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
            message.setFrom(address);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(_workflow.getReceiver().getEmail()));
            message.setSubject("Была создана новая задача: " +
                    _workflow.getAssignee().getLastName() +
                    " -- >" + _workflow.getReceiver().getLastName(), "utf-8");

            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();

            htmlPart.setContent("<html><body><h2>Задача № " +
                    _workflow.getTask().getInternalNumber() + "</h2>" +
                    "<p style=\"font-family:Arial;font-size:12px;\">Задачу назначил: " + _workflow.getAssignee().getFirstName() +
                    " " + _workflow.getAssignee().getMiddleName() + " " +
                    _workflow.getAssignee().getLastName() + "</p>" +
                    "<p style=\"font-family:Arial;font-size:12px;\">Резолюция к задаче: " + _workflow.getDescription() +
                    "</p><a href=\"http://sgnhp.snos.ru:8080/Workflow\">Просмотреть задачу</a>" +
                    "<p>Есть вопрос? Звоните: 21-64. Алексей.</p>" +
                    "</body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void sendmailChangeState(WorkflowBean _workflow) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
            message.setFrom(address);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(_workflow.getAssignee().getEmail()));
            message.setSubject("Изменение статуса задачи", "utf-8");

            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();

            StateBean bean = stateManagerService.getStateByStateUid(Integer.parseInt(_workflow.getState()));

            htmlPart.setContent("<html><body>" +
                    "<p style=\"font-family:Arial;font-size:12px;\">Изменение статуса у назначенной Вами задачи № " +
                    _workflow.getTask().getInternalNumber() +" ("+
                    _workflow.getTask().getDescription()+")</p>" +
                    "<p style=\"font-family:Arial;font-size:12px;\">Получатель задачи: " +
                    _workflow.getReceiver().getLastName() + " " +
                    _workflow.getReceiver().getFirstName() + " " +
                    _workflow.getReceiver().getMiddleName() + "</p>" +
                    "<p style=\"font-family:Arial;font-size:12px;\">Резолюция к задаче: " +
                    _workflow.getTask().getDescription() + "</p>" +
                    "<p style=\"font-family:Arial;font-size:12px;\">Текущий статус задачи: " + bean.getStateDescription() + "</p></body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void sendmailRemind(WorkflowBean _workflow) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
            message.setFrom(address);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(_workflow.getReceiver().getEmail()));
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(_workflow.getAssignee().getEmail()));
            message.setSubject("Запрос о выполнении задачи", "utf-8");

            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();

            htmlPart.setContent("<html><body>" +
                    "<p style=\"font-family:Arial;font-size:12px;\">Прошу предоставить отчет о состоянии задачи № " +
                    _workflow.getTask().getInternalNumber() + "</p>" +
                    "<p style=\"font-family:Arial;font-size:12px;\">Задачу назначил: " +
                    _workflow.getAssignee().getLastName() + " " +
                    _workflow.getAssignee().getFirstName() + " " +
                    _workflow.getAssignee().getMiddleName() + "</p>" +
                    "<p style=\"font-family:Arial;font-size:12px;\">Резолюция к задаче: " +
                    _workflow.getDescription() + "</p>" +
                    "<p style=\"font-family:Arial;font-size:12px;\">Текущий статус задачи: " +
                    _workflow.getState() + "</p></body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void sendmailSheduler(ArrayList<WorkflowBean> wfs) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
            message.setFrom(address);
            WorkflowBean bean = (WorkflowBean) wfs.toArray()[0];
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(bean.getReceiver().getEmail()));
            message.setSubject("У Вас есть невыполненные задачи!", "utf-8");

            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();

            String tableBody = "";

            for (WorkflowBean wf : wfs) {
                tableBody += "<tr>" +
                        "<td>" + wf.getTask().getInternalNumber() + "</td>" +
                        "<td>" + wf.getTask().getDescription() + "</td>" +
                        "<td>" + wf.getTask().getStartDate() + "</td>" +
                        "<td>" + wf.getTask().getDueDate() + "</td>" +
                        "<td>" + wf.getAssignee().getLastName() + " " +
                        wf.getAssignee().getFirstName() + " " +
                        wf.getAssignee().getMiddleName() + "</td>" +
                        "<td>" + wf.getDescription() + "</td>" +
                        "</tr>";
            }
            htmlPart.setContent("<html><head><style type=\"text/css\"> " +
                    "body {font-family:Arial;font-size:small;}" +
                    "table {font-family:Arial; font-size:8pt;border-collapse:collapse}" +
                    "td {border: 1px solid #000000;}" +
                    "</style></style></head><body>" +
                    "<p>" +
                    "Вам были назначены следующие задания:" +
                    "</p>" +
                    "<table width=100%>" +
                    "<tr align=center>" +
                    "<td width=10%>Номер задачи</td>" +
                    "<td width=30%>Описание задачи</td>" +
                    "<td width=10%>Дата начала</td>" +
                    "<td width=10%>Срок до</td>" +
                    "<td width=20%>Задачу поставил</td>" +
                    "<td width=20%>Резолюция к задаче</td>" +
                    "</tr>" +
                    tableBody +
                    "</table>" +
                    "<a href=\"http://sgnhp.snos.ru:8080/Workflow\">Просмотреть задачи</a>" +
                    "<p>Есть вопрос? Звоните: 21-64. Алексей.</p>" +
                    "</body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public IStateManagerService getStateManagerService() {
        return stateManagerService;
    }

    public void setStateManagerService(IStateManagerService stateManagerService) {
        this.stateManagerService = stateManagerService;
    }

    public void setMailHostName(String mailHostName) {
        this.mailHostName = mailHostName;
    }

    /**
     * @param fromAddress the fromAddress to set
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * @param fromName the fromName to set
     */
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    /**
     * @param userManagerService the userManagerService to set
     */
    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    /**
     * @return the mailHostName
     */
    public String getMailHostName() {
        return mailHostName;
    }

    /**
     * @return the fromAddress
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * @return the fromName
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * @return the userManagerService
     */
    public IUserManagerService getUserManagerService() {
        return userManagerService;
    }
}
