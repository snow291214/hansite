package ru.sgnhp.service;

import java.util.Date;
import java.util.List;
import ru.sgnhp.domain.OutgoingMailBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****/
public interface IOutgoingMailService extends IGenericService<OutgoingMailBean, Long> {

    OutgoingMailBean getByOutgoingNumber(Long outgoingNumber);

    List<OutgoingMailBean> getByOutgoingDate(Date outgoingDate);

    OutgoingMailBean getByDocumentumNumber(String documentumNumber);

    List<OutgoingMailBean> getByReceiverCompany(String receiverCompany);

    List<OutgoingMailBean> getByReceiverName(String receiverName);

    List<OutgoingMailBean> getByResponsibleName(String responsibleName);

    List<OutgoingMailBean> getByDueDate(Date dueDate);

    Long getNewOutgoingNumber();
}
