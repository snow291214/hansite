package ru.sgnhp.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.DateUtils;
import ru.sgnhp.Translit;
import ru.sgnhp.domain.DocumentBean;
import ru.sgnhp.domain.FileBean;
import ru.sgnhp.domain.OutgoingFileBean;
import ru.sgnhp.domain.OutgoingMailBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.service.IMailService;
import ru.sgnhp.service.IWorkflowManagerService;

/**
 * ***
 *
 * @author Alexey Khudyakov @Skype: khudyakov.alexey
 *
 *****
 */
public class MailServiceImpl implements IMailService {

    private String mailHostName;
    private String fromAddress;
    private String fromName;
    private String applicationPath;
    private IWorkflowManagerService workflowManagerService;
//    private IUserManagerService userManagerService;
//    private IStateManagerService stateManagerService;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public void sendmailAssign(WorkflowBean _workflow) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
            message.addHeader("X-Priority", "1");
            message.setFrom(address);
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(_workflow.getReceiver().getEmail()));
            message.setSubject("Была создана новая задача: "
                    + "[" + _workflow.getTaskBean().getInternalNumber() + "] "
                    + _workflow.getAssignee().getLastName()
                    + " == > "
                    + _workflow.getReceiver().getLastName(), "utf-8");

            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();

            htmlPart.setContent("<html><body><h2>Номер задачи:  "
                    + _workflow.getTaskBean().getInternalNumber() + "</h2>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Задачу передал: "
                    + _workflow.getAssignee().getFirstName()
                    + " " + _workflow.getAssignee().getMiddleName() + " "
                    + _workflow.getAssignee().getLastName() + "</p>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Описание задачи: "
                    + _workflow.getTaskBean().getDescription() + "</p>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Резолюция к задаче: "
                    + _workflow.getDescription() + "</p>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">"
                    + "Во вложении письма электронная копия документа</p>"
                    //+ "<a href=\"" + this.applicationPath
                    //+ "\">Просмотреть все задачи</a> <br />"
                    //+ "<a href=\"" + this.applicationPath + "workflowManager.htm?workflowID="
                    //+ _workflow.getUid().toString() + "\">Просмотреть задачу</a>"
                    //+ "<p>Есть вопрос? Звоните: 17-70. Алексей.</p>"
                    + "</body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);

            Translit translit = new Translit();
            // Part two is attachment
            //htmlPart = new MimeBodyPart();
            Set<FileBean> fileBeans = _workflow.getTaskBean().getFilesSet();
            for (FileBean fileBean : fileBeans) {
                htmlPart = new MimeBodyPart();
                DataSource dataSource = new ByteArrayDataSource(fileBean.getBlobField(), "application/x-any");
                htmlPart.setDataHandler(new DataHandler(dataSource));
                htmlPart.setFileName(translit.toTranslit(fileBean.getFileName()));
                multipart.addBodyPart(htmlPart);
            }
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            Logger logger = Logger.getLogger("MailServiceImpl");
            logger.log(Level.WARNING, "MailServiceImpl");
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public void sendmailChangeState(WorkflowBean _workflow) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
            message.setFrom(address);

            /*
             * Тупое решение, но тем не менее
             */
            if (_workflow.getState().getStateUid() == 3L) {
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(_workflow.getReceiver().getEmail()));
            } else {
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(_workflow.getAssignee().getEmail()));
            }

            message.setSubject("Изменение статуса задачи на '"
                    + _workflow.getState().getStateDescription() + "': "
                    + "[" + _workflow.getTaskBean().getInternalNumber() + "] "
                    + _workflow.getAssignee().getLastName()
                    + " == > "
                    + _workflow.getReceiver().getLastName(), "utf-8");

            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();

            htmlPart.setContent("<html><body>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Изменение статуса у назначенной Вами задачи № "
                    + _workflow.getTaskBean().getInternalNumber() + " ("
                    + _workflow.getTaskBean().getDescription() + ")</p>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Получатель задачи: "
                    + _workflow.getReceiver().getLastName() + " "
                    + _workflow.getReceiver().getFirstName() + " "
                    + _workflow.getReceiver().getMiddleName() + "</p>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Описание задачи: "
                    + _workflow.getTaskBean().getDescription() + "</p>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Резолюция к задаче: "
                    + _workflow.getDescription() + "</p>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Текущий статус задачи: "
                    + _workflow.getState().getStateDescription() + "</p>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Записка к смене статуса: "
                    + _workflow.getWorkflowNote() + "</p>"
                    + "<a href=\"" + this.applicationPath
                    + "\">Просмотреть все задачи</a> <br />"
                    + "<a href=\"" + this.applicationPath + "workflowManager.htm?workflowID="
                    + _workflow.getUid().toString() + "\">Просмотреть задачу</a>"
                    + "</body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
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

            htmlPart.setContent("<html><body>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Прошу предоставить отчет о состоянии задачи № "
                    + _workflow.getTaskBean().getInternalNumber() + "</p>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Задачу назначил: "
                    + _workflow.getAssignee().getLastName() + " "
                    + _workflow.getAssignee().getFirstName() + " "
                    + _workflow.getAssignee().getMiddleName() + "</p>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Резолюция к задаче: "
                    + _workflow.getDescription() + "</p>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Текущий статус задачи: "
                    + _workflow.getState().getStateDescription() + "</p></body></html>",
                    "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
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
                tableBody += "<tr>"
                        + "<td>" + wf.getTaskBean().getInternalNumber() + "</td>"
                        + "<td>" + wf.getTaskBean().getDescription() + "</td>"
                        + "<td>" + DateUtils.dateToString(wf.getTaskBean().getStartDate(), "dd.MM.yyyy") + "</td>"
                        + "<td>" + DateUtils.dateToString(wf.getTaskBean().getDueDate(), "dd.MM.yyyy") + "</td>"
                        + "<td>" + wf.getAssignee().getLastName() + " "
                        + wf.getAssignee().getFirstName() + " "
                        + wf.getAssignee().getMiddleName() + "</td>"
                        + "<td>" + wf.getDescription() + "</td>"
                        + "</tr>";
            }
            htmlPart.setContent("<html><head><style type=\"text/css\"> "
                    + "body {font-family:Arial;font-size:small;}"
                    + "table {font-family:Arial; font-size:8pt;border-collapse:collapse}"
                    + "td {border: 1px solid #000000;}"
                    + "</style></style></head><body>"
                    + "<p>"
                    + "Вам были назначены следующие задания:"
                    + "</p>"
                    + "<table width=100%>"
                    + "<tr align=center>"
                    + "<td width=10%>Номер задачи</td>"
                    + "<td width=30%>Описание задачи</td>"
                    + "<td width=10%>Дата начала</td>"
                    + "<td width=10%>Срок до</td>"
                    + "<td width=20%>Задачу поставил</td>"
                    + "<td width=20%>Резолюция к задаче</td>"
                    + "</tr>"
                    + tableBody
                    + "</table>"
                    + "<a href=\"" + this.applicationPath + "\">Просмотреть задачи</a>"
                    + "<p>Есть вопрос? Звоните: 17-70. Алексей.</p>"
                    + "</body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public void sendmailReport(List<WorkflowBean> wfs) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
            message.setFrom(address);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(wfs.get(0).getAssignee().getEmail()));
            message.setSubject("Отчет о назначенных, но еще не выполненых, задачах.", "utf-8");

            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();

            String tableBody = "";
            int counter = 1;
            String lastName = "";
            for (WorkflowBean wf : wfs) {
                if (!lastName.equals(wf.getReceiver().getLastName())) {
                    tableBody += "<tr>"
                            + "<td colspan = 6><br /><b>"
                            + wf.getReceiver().getLastName() + " "
                            + wf.getReceiver().getFirstName() + " "
                            + wf.getReceiver().getMiddleName() + " "
                            + "</b><br />&nbsp;</td>"
                            + "</tr>";
                    lastName = wf.getReceiver().getLastName();
                    counter = 1;
                }

                if ((wf.getState().getStateUid() == 1L) & (getWorkflowManagerService().isWorkflowActive(wf.getUid()))) {
                    ArrayList<WorkflowBean> roadmap = new ArrayList<WorkflowBean>();
                    roadmap.add(wf);
                    roadmap = this.getWorkflowManagerService().getWorkflowMembersByWorkflowUid(wf.getUid(), wf.getParentUid(), roadmap);
                    Collections.reverse(roadmap);
                    tableBody += "<tr>"
                            + "<td>" + counter + "</td>"
                            + "<td>"
                            + "Компания: " + wf.getTaskBean().getExternalCompany() +". "
                            + wf.getTaskBean().getDescription() + "</td>"
                            + "<td>"
                            + wf.getReceiver().getLastName() + " "
                            + wf.getReceiver().getFirstName() + " "
                            + wf.getReceiver().getMiddleName() + " "
                            + "</td>"
                            + "<td>"
                            + DateUtils.dateToString(wf.getAssignDate(), "dd.MM.yyyy")
                            + "</td>"
                            + "<td>"
                            + DateUtils.dateToString(wf.getTaskBean().getDueDate(), "dd.MM.yyyy")
                            + "</td>"
                            + "<td>" + wf.getState().getStateDescription() + "</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td><br />"
                            + " Движение задачи по исполнителям: "
                            + "<br />&nbsp;</td>"
                            + "<td colspan=5><table width=100%>";
                    int c = 1;
                    tableBody += "<tr>"
                            + "<td>Номер п/п</td>"
                            + "<td>Задачу назначил</td>"
                            + "<td>Задачу получил</td>"
                            + "<td>Дата получения задачи</td>"
                            + "<td>Резолюция к задаче</td>"
                            + "<td>Состояние задачи</td>"
                            + "</tr>";
                    for (WorkflowBean w : roadmap) {
                        tableBody += "<tr>"
                                + "<td>" + c + "</td>"
                                + "<td>"
                                + w.getAssignee().getLastName() + " "
                                + w.getAssignee().getFirstName() + " "
                                + w.getAssignee().getMiddleName()
                                + "</td>"
                                + "<td>"
                                + w.getReceiver().getLastName() + " "
                                + w.getReceiver().getFirstName() + " "
                                + w.getReceiver().getMiddleName()
                                + "</td>"
                                + "<td>"
                                + DateUtils.dateToString(w.getAssignDate(), "dd.MM.yyyy")
                                + "</td>"
                                + "<td>"
                                + w.getDescription()
                                + "</td>"
                                + "<td>"
                                + w.getState().getStateDescription()
                                + "</td>"
                                + "</tr>";
                        c++;
                    }
                    tableBody += "</table></td></tr>";
                } else if ((wf.getState().getStateUid() == 1L) & (!getWorkflowManagerService().isWorkflowActive(wf.getUid()))) {
                    continue;
                } else {
                    tableBody += "<tr>"
                            + "<td>" + counter + "</td>"
                            + "<td>"
                            + "Компания: " + wf.getTaskBean().getExternalCompany() +". "
                            + wf.getTaskBean().getDescription()
                            + "</td>"
                            + "<td>"
                            + wf.getReceiver().getLastName() + " "
                            + wf.getReceiver().getFirstName() + " "
                            + wf.getReceiver().getMiddleName() + " "
                            + "</td>"
                            + "<td>"
                            + DateUtils.dateToString(wf.getAssignDate(), "dd.MM.yyyy")
                            + "</td>"
                            + "<td>"
                            + DateUtils.dateToString(wf.getTaskBean().getDueDate(), "dd.MM.yyyy")
                            + "</td>"
                            + "<td>" + wf.getState().getStateDescription() + "</td>"
                            + "</tr>";
                }

                counter++;
            }

            htmlPart.setContent("<html><head><style type=\"text/css\"> "
                    + "body {font-family:Arial;font-size:small;}"
                    + "table {font-family:Arial; font-size:12pt;border-collapse:collapse;}"
                    + "td {border: 1px solid #000000;;margins: 5px 5px 5px 5px;}"
                    + "</style></style></head><body>"
                    + "<p> Уважаемый (ая) коллега!</p>"
                    + "<p>"
                    + "Вами были назначены задания, но они еще не выполнены исполнителями."
                    + "</p>"
                    + "<table width=100%>"
                    + "<tr align=center>"
                    + "<td width=5%>Номер п/п</td>"
                    + "<td width=45%>Текст задачи</td>"
                    + "<td width=20%>Задачу получил</td>"
                    + "<td width=10%>Дата назначения задачи</td>"
                    + "<td width=10%>Срок выполнения задачи</td>"
                    //+ "<td width=30%>Резолюция к задаче</td>"
                    + "<td width=10%>Состояние задачи</td>"
                    + "</tr>"
                    + tableBody
                    + "</table>"
                    + "<br />"
                    + "<a href=\"" + this.applicationPath + "\">Просмотреть задачи</a>"
                    + "<p>Есть вопрос? Звоните: 17-70. Алексей.</p>"
                    + "</body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public void sendmailReportForDirector(List<WorkflowBean> wfs) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
            message.setFrom(address);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("reception@salavatmed.ru"));
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress("77han@salavatmed.ru"));
            message.setSubject("Отчет о назначенных, но еще не выполненых, задачах.", "utf-8");

            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();

            String tableBody = "";
            int counter = 1;
            String lastName = "";
            for (WorkflowBean wf : wfs) {
                if (!lastName.equals(wf.getReceiver().getLastName())) {
                    tableBody += "<tr>"
                            + "<td colspan = 5><br /><b>"
                            + wf.getReceiver().getLastName() + " "
                            + wf.getReceiver().getFirstName() + " "
                            + wf.getReceiver().getMiddleName() + " "
                            + "</b><br />&nbsp;</td>"
                            + "</tr>";
                    lastName = wf.getReceiver().getLastName();
                    counter = 1;
                }

                if ((wf.getState().getStateUid() == 1L) & (getWorkflowManagerService().isWorkflowActive(wf.getUid()))) {
                    ArrayList<WorkflowBean> roadmap = new ArrayList<WorkflowBean>();
                    roadmap.add(wf);
                    roadmap = this.getWorkflowManagerService().getWorkflowMembersByWorkflowUid(wf.getUid(), wf.getParentUid(), roadmap);
                    Collections.reverse(roadmap);
                    tableBody += "<tr>"
                            + "<td>" + counter + "</td>"
                            + "<td>"
                            + "Компания: " + wf.getTaskBean().getExternalCompany() 
                            +". "
                            + wf.getTaskBean().getDescription() + "</td>"
//                            + "<td>"
//                            + wf.getReceiver().getLastName() + " "
//                            + wf.getReceiver().getFirstName() + " "
//                            + wf.getReceiver().getMiddleName() + " "
//                            + "</td>"
                            + "<td>"
                            + DateUtils.dateToString(wf.getAssignDate(), "dd.MM.yyyy")
                            + "</td>"
                            + "<td>"
                            + DateUtils.dateToString(wf.getTaskBean().getDueDate(), "dd.MM.yyyy")
                            + "</td>"
                            + "<td>В работе у исполнителя</td>"
                            + "</tr>";
                } else if ((wf.getState().getStateUid() == 1L) & (!getWorkflowManagerService().isWorkflowActive(wf.getUid()))) {
                    continue;
                } else {
                    tableBody += "<tr>"
                            + "<td>" + counter + "</td>"
                            + "<td>"
                            + "Компания: " + wf.getTaskBean().getExternalCompany() +". "
                            + wf.getTaskBean().getDescription()
                            + "</td>"
//                            + "<td>"
//                            + wf.getReceiver().getLastName() + " "
//                            + wf.getReceiver().getFirstName() + " "
//                            + wf.getReceiver().getMiddleName() + " "
//                            + "</td>"
                            + "<td>"
                            + DateUtils.dateToString(wf.getAssignDate(), "dd.MM.yyyy")
                            + "</td>"
                            + "<td>"
                            + DateUtils.dateToString(wf.getTaskBean().getDueDate(), "dd.MM.yyyy")
                            + "</td>"
                            + "<td>" + wf.getState().getStateDescription() + "</td>"
                            + "</tr>";
                }

                counter++;
            }

            htmlPart.setContent("<html><head><style type=\"text/css\"> "
                    + "body {font-family:Arial;font-size:small;}"
                    + "table {font-family:Arial; font-size:12pt;border-collapse:collapse;}"
                    + "td {border: 1px solid #000000;;margins: 5px 5px 5px 5px;}"
                    + "</style></style></head><body>"
                    + "<p> Уважаемый руководитель!</p>"
                    + "<p>"
                    + "Вами были назначены задания, но они еще не выполнены исполнителями."
                    + "</p>"
                    + "<table width=100%>"
                    + "<tr align=center>"
                    + "<td width=5%>Номер п/п</td>"
                    + "<td width=45%>Текст задачи</td>"
//                    + "<td width=20%>Задачу получил</td>"
                    + "<td width=10%>Дата назначения задачи</td>"
                    + "<td width=10%>Срок выполнения задачи</td>"
                    //+ "<td width=30%>Резолюция к задаче</td>"
                    + "<td width=10%>Состояние задачи</td>"
                    + "</tr>"
                    + tableBody
                    + "</table>"
                    + "</body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void setMailHostName(String mailHostName) {
        this.mailHostName = mailHostName;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getMailHostName() {
        return mailHostName;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public String getFromName() {
        return fromName;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public void sendmailOutgoing(OutgoingMailBean outgoingMailBean) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
            message.addHeader("X-Priority", "1");
            message.setFrom(address);
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(outgoingMailBean.getWorkflowUserBean().getEmail()));
            message.setSubject("Зарегистрировано и отправлено письмо: "
                    + outgoingMailBean.getWorkflowUserBean().getLastName()
                    + " ==> " + outgoingMailBean.getReceiverName(), "utf-8");

            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();

            htmlPart.setContent("<html><body><h2>Исходящее письмо №"
                    + outgoingMailBean.getOutgoingNumber().toString() + "</h2>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Тема письма: "
                    + outgoingMailBean.getDescription() + "</p>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Компания-получатель: "
                    + outgoingMailBean.getReceiverCompany() + ". ФИО получателя: " + outgoingMailBean.getReceiverName()
                    + "</body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);

            Translit translit = new Translit();
            // Part two is attachment
            //htmlPart = new MimeBodyPart();
            Set<OutgoingFileBean> fileBeans = outgoingMailBean.getOutgoingFileBeanSet();
            for (OutgoingFileBean fileBean : fileBeans) {
                htmlPart = new MimeBodyPart();
                DataSource dataSource = new ByteArrayDataSource(fileBean.getBlobField(), "application/x-any");
                htmlPart.setDataHandler(new DataHandler(dataSource));
                htmlPart.setFileName(translit.toTranslit(fileBean.getFileName()));
                multipart.addBodyPart(htmlPart);
            }

            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            Logger logger = Logger.getLogger("MailServiceImpl");
            logger.info(e.getMessage());
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public void tasksForReviewReport(List<WorkflowBean> wfs) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
            message.setFrom(address);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(wfs.get(0).getAssignee().getEmail()));
            message.setSubject("Отчет о задачах, ожидающих Вашей проверки.", "utf-8");

            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();

            String tableBody = "";
            int counter = 1;
            for (WorkflowBean wf : wfs) {
                tableBody += "<tr>"
                        + "<td>" + counter + "</td>"
                        + "<td>" + wf.getTaskBean().getDescription() + "</td>"
                        + "<td>"
                        + wf.getAssignee().getLastName() + " "
                        + wf.getAssignee().getFirstName() + " "
                        + wf.getAssignee().getMiddleName()
                        + "</td>"
                        + "<td>"
                        + wf.getReceiver().getLastName() + " "
                        + wf.getReceiver().getFirstName() + " "
                        + wf.getReceiver().getMiddleName() + " "
                        + "</td>"
                        + "<td>" + wf.getDescription() + "</td>"
                        + "<td>" + wf.getState().getStateDescription() + "</td>"
                        + "<td>" + wf.getWorkflowNote() + "</td>"
                        + "<td><a href=\"" + this.applicationPath + "roadmap.htm?workflowID="
                        + wf.getUid() + "\">" + wf.getUid() + "</a></td>"
                        + "</tr>";
                counter++;
            }
            htmlPart.setContent("<html><head><style type=\"text/css\"> "
                    + "body {font-family:Arial;font-size:small;}"
                    + "table {font-family:Arial; font-size:8pt;border-collapse:collapse}"
                    + "td {border: 1px solid #000000;}"
                    + "</style></style></head><body>"
                    + "<p> Уважаемый (ая) коллега!</p>"
                    + "<p>"
                    + "Исполнители сохранили для Вашей проверки нижеследующие задачи."
                    + "</p>"
                    + "<table width=100%>"
                    + "<tr align=center>"
                    + "<td width=10%>Номер п/п</td>"
                    + "<td width=20%>Текст задачи</td>"
                    + "<td width=20%>Задачу назначил</td>"
                    + "<td width=20%>Задачу получил</td>"
                    + "<td width=30%>Резолюция к задаче</td>"
                    + "<td width=5%>Состояние задачи</td>"
                    + "<td width=10%>Записка к задаче</td>"
                    + "<td width=5%>Ссылка на задачу</td>"
                    + "</tr>"
                    + tableBody
                    + "</table>"
                    + "<br />"
                    + "<a href=\"" + this.applicationPath + "tasksForReview.htm\">Просмотреть и проверить выполненные задачи</a>"
                    + "<p>Есть вопрос? Звоните: 17-70. Алексей.</p>"
                    + "</body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public void sendmailOrder(DocumentBean documentBean) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
            message.setFrom(address);
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(documentBean.getContactPerson().getEmail()));
            message.setSubject("Зарегистрирован распорядительный документ", "utf-8");

            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();

            htmlPart.setContent("<html><body><h2>Зарегистрирован(о) "
                    + documentBean.getDocumentTypeBean().getDescription() + " №"
                    + documentBean.getDocumentNumber() + "</h2>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Тема распорядительного документа: "
                    + documentBean.getDescription() + "</p>"
                    + "</body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            Logger logger = Logger.getLogger("MailServiceImpl");
            logger.log(Level.WARNING, "MailServiceImpl");
        }
    }

    public String getApplicationPath() {
        return applicationPath;
    }

    public void setApplicationPath(String applicationPath) {
        this.applicationPath = applicationPath;
    }

    /**
     * @return the workflowManagerService
     */
    public IWorkflowManagerService getWorkflowManagerService() {
        return workflowManagerService;
    }

    /**
     * @param workflowManagerService the workflowManagerService to set
     */
    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
