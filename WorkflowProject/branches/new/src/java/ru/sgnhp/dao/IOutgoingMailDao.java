package ru.sgnhp.dao;

import java.util.Date;
import java.util.List;
import ru.sgnhp.domain.OutgoingMailBean;

public interface IOutgoingMailDao extends IGenericDao<OutgoingMailBean, Long> {

    List<OutgoingMailBean> getByOutgoingNumber(Long outgoingNumber);

    List<OutgoingMailBean> getByDescription(String description);

    List<OutgoingMailBean> getByOutgoingDate(Date outgoingDate);

    List<OutgoingMailBean> getByDueDate(Date dueDate);

    List<OutgoingMailBean> getByPeriodOfDate(Date outgoingDate, Date dueDate);

    List<OutgoingMailBean> getByDocumentumNumber(String documentumNumber);

    List<OutgoingMailBean> getByReceiverCompany(String receiverCompany);

    List<OutgoingMailBean> getByReceiverName(String receiverName);

    List<OutgoingMailBean> getByResponsibleUid(Long responsibleUid);

    Long getNewOutgoingNumber();
}
