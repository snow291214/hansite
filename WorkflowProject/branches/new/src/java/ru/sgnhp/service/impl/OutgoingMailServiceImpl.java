package ru.sgnhp.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.IOutgoingMailDao;
import ru.sgnhp.domain.OutgoingMailBean;
import ru.sgnhp.service.IOutgoingMailService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class OutgoingMailServiceImpl extends GenericServiceImpl<OutgoingMailBean, Long> implements IOutgoingMailService {

    private IOutgoingMailDao outgoingMailDao;

    public OutgoingMailServiceImpl(IGenericDao<OutgoingMailBean, Long> genericDao) {
        super(genericDao);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<OutgoingMailBean> getByOutgoingNumber(Long outgoingNumber) {
        return outgoingMailDao.getByOutgoingNumber(outgoingNumber);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<OutgoingMailBean> getByDescription(String description) {
        return outgoingMailDao.getByDescription("%" + description + "%");
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<OutgoingMailBean> getByOutgoingDate(Date outgoingDate) {
        return outgoingMailDao.getByOutgoingDate(outgoingDate);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<OutgoingMailBean> getByDocumentumNumber(String documentumNumber) {
        return outgoingMailDao.getByDocumentumNumber("%" + documentumNumber + "%");
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<OutgoingMailBean> getByReceiverCompany(String receiverCompany) {
        return outgoingMailDao.getByReceiverCompany("%" + receiverCompany + "%");
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<OutgoingMailBean> getByReceiverName(String receiverName) {
        return outgoingMailDao.getByReceiverName("%" + receiverName + "%");
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<OutgoingMailBean> getByResponsibleUid(Long responsibleUid) {
        return outgoingMailDao.getByResponsibleUid(responsibleUid);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<OutgoingMailBean> getByDueDate(Date dueDate) {
        return outgoingMailDao.getByDueDate(dueDate);
    }

    public void setOutgoingMailDao(IOutgoingMailDao outgoingMailDao) {
        this.outgoingMailDao = outgoingMailDao;
    }

    public Long getNewOutgoingNumber() {
        Long value = outgoingMailDao.getNewOutgoingNumber();
        if (value == -1) {
            value++;
        }
        return value + 1;
    }
}
