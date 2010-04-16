package ru.sgnhp.service.impl;

import com.sun.istack.internal.ByteArrayDataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.domain.DocumentBean;
import ru.sgnhp.domain.FileBean;
import ru.sgnhp.domain.OutgoingMailBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.service.IMailService;

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
    private String applicationPath;
//    private IWorkflowManagerService workflowManagerService;
//    private IUserManagerService userManagerService;
//    private IStateManagerService stateManagerService;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void sendmailAssign(WorkflowBean _workflow) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
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

            htmlPart.setContent("<html><body><h2>Задача № "
                    + _workflow.getTaskBean().getInternalNumber() + "</h2>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Задачу назначил: "
                    + _workflow.getAssignee().getFirstName()
                    + " " + _workflow.getAssignee().getMiddleName() + " "
                    + _workflow.getAssignee().getLastName() + "</p>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">Резолюция к задаче: "
                    + _workflow.getDescription() + "</p>"
                    + "<p style=\"font-family:Arial;font-size:12px;\">"
                    + "Идентификатор работы в СКП 'Primavera': "
                    + _workflow.getTaskBean().getPrimaveraUid() + "</p>"
                    + "<a href=\""+ this.applicationPath
                    + "\">Просмотреть все задачи</a> <br />"
                    + "<a href=\"" + this.applicationPath + "workflowManager.htm?workflowID="
                    + _workflow.getUid().toString() + "\">Просмотреть задачу</a>"
                    + "<p>Есть вопрос? Звоните: 21-64. Алексей.</p>"
                    + "</body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);

            // Part two is attachment
            htmlPart = new MimeBodyPart();
            Set<FileBean> fileBeans = _workflow.getTaskBean().getFilesSet();
            for (FileBean fileBean : fileBeans) {
                DataSource dataSource = new ByteArrayDataSource(fileBean.getBlobField(), "application/x-any");
                htmlPart.setDataHandler(new DataHandler(dataSource));
                htmlPart.setFileName(fileBean.getFileName());
                multipart.addBodyPart(htmlPart);
            }
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void sendmailChangeState(WorkflowBean _workflow) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
            message.setFrom(address);

            /* Тупое решение, но тем не менее */
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
                    + "<a href=\"http://sgnhp.snos.ru:8080/Workflow/"
                    + "\">Просмотреть все задачи</a> <br />"
                    + "<a href=\"http://sgnhp.snos.ru:8080/Workflow/workflowManager.htm?workflowID="
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
                        + "<td>" + wf.getTaskBean().getStartDate() + "</td>"
                        + "<td>" + wf.getTaskBean().getDueDate() + "</td>"
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
                    + "<a href=\"http://sgnhp.snos.ru:8080/Workflow\">Просмотреть задачи</a>"
                    + "<p>Есть вопрос? Звоните: 21-64. Алексей.</p>"
                    + "</body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
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
                        + "<td><a href=\"http://sgnhp.snos.ru:8080/Workflow/roadmap.htm?workflowID="
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
                    + "Вами были назначены задания, но они еще не выполнены исполнителями."
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
                    + "<a href=\"http://sgnhp.snos.ru:8080/Workflow\">Просмотреть задачи</a>"
                    + "<p>Есть вопрос? Звоните: 21-64. Алексей.</p>"
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
    public void sendmailOutgoing(OutgoingMailBean outgoingMailBean) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailHostName);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress address = new InternetAddress(fromAddress);
            address.setPersonal(fromName, "utf-8");
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
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
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
                        + "<td><a href=\"http://sgnhp.snos.ru:8080/Workflow/roadmap.htm?workflowID="
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
                    + "<a href=\"http://sgnhp.snos.ru:8080/Workflow/tasksForReview.htm\">Просмотреть и проверить выполненные задачи</a>"
                    + "<p>Есть вопрос? Звоните: 21-64. Алексей.</p>"
                    + "</body></html>", "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
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
            e.printStackTrace();
        }
    }

    public String getApplicationPath() {
        return applicationPath;
    }

    public void setApplicationPath(String applicationPath) {
        this.applicationPath = applicationPath;
    }
}
