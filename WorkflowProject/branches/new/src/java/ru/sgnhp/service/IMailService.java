/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.service;

import java.util.ArrayList;
import java.util.List;
import ru.sgnhp.domain.DocumentBean;
import ru.sgnhp.domain.OutgoingMailBean;
import ru.sgnhp.domain.WorkflowBean;

/**
 *
 * @author 48han
 */
public interface IMailService {

    void sendmailAssign(WorkflowBean _workflow);

    void sendmailChangeState(WorkflowBean _workflow);

    void sendmailRemind(WorkflowBean _workflow);

    void sendmailSheduler(ArrayList<WorkflowBean> wfs);

    void sendmailReport(List<WorkflowBean> wfs);

    void sendmailOutgoing(OutgoingMailBean outgoingMailBean);

    void tasksForReviewReport(List<WorkflowBean> wfs);

    void sendmailOrder(DocumentBean documentBean);
}
