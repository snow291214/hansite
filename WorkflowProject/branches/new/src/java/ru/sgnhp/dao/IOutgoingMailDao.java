package ru.sgnhp.dao;

import java.util.Date;
import java.util.List;
import ru.sgnhp.domain.OutgoingMailBean;

public interface IOutgoingMailDao extends IGenericDao<OutgoingMailBean, Long> {

    OutgoingMailBean getByOutgoingNumber(Long outgoingNumber);

    List<OutgoingMailBean> getByOutgoingDate(Date outgoingDate);

    OutgoingMailBean getByDocumentumNumber(String documentumNumber);

    List<OutgoingMailBean> getByReceiverCompany(String receiverCompany);

    List<OutgoingMailBean> getByReceiverName(String receiverName);

    List<OutgoingMailBean> getByResponsibleName(String responsibleName);

    List<OutgoingMailBean> getByDueDate(Date dueDate);

    Long getNewOutgoingNumber();
}
