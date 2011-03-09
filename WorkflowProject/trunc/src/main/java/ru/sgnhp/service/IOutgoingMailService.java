package ru.sgnhp.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import ru.sgnhp.domain.OutgoingMailBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****/
public interface IOutgoingMailService extends IGenericService<OutgoingMailBean, Long> {

    List<OutgoingMailBean> getByOutgoingNumber(Long outgoingNumber);

    List<OutgoingMailBean> getByDescription(String description);

    List<OutgoingMailBean> getByOutgoingDate(Date outgoingDate);

    List<OutgoingMailBean> getByPeriodOfDate(Date outgoingDate, Date dueDate);

    List<OutgoingMailBean> getByDocumentumNumber(String documentumNumber);

    List<OutgoingMailBean> getByReceiverCompany(String receiverCompany);

    List<OutgoingMailBean> getByReceiverName(String receiverName);

    List<OutgoingMailBean> getByResponsibleUid(Long responsibleUid);

    List<OutgoingMailBean> getByDueDate(Date dueDate);

    List<OutgoingMailBean> getByPrimaveraUid(String primaveraUid);

    Long getNewOutgoingNumber();

    List<OutgoingMailBean> getAllOutgoingMailByYear(Integer currentYear) throws ParseException;

    void dailyReport();

    List<String> getAllOutgoingMailWithPrimaveraUid();
}
