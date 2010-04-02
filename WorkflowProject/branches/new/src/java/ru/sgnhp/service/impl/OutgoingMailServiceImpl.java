package ru.sgnhp.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Long getNewOutgoingNumber() {
        Long value = outgoingMailDao.getNewOutgoingNumber();
        if (value == -1) {
            value++;
        }
        return value + 1;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<OutgoingMailBean> getByPeriodOfDate(Date outgoingDate, Date dueDate) {
        return outgoingMailDao.getByPeriodOfDate(outgoingDate, dueDate);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<OutgoingMailBean> getAllOutgoingMailByYear(Integer currentYear) throws ParseException {
        return outgoingMailDao.getAllIncomingMailByYear(currentYear);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void dailyReport() {
        Calendar today = Calendar.getInstance();
        //File file = new File(String.format("/media/storage/doc/скан/исходящие %1$tY/%2$s/", today, "incomingMail.csv"));
        try {
            FileWriter outFile = new FileWriter(
                    String.format("/media/storage/doc/скан/исходящие %1$tY/%2$s/",
                    //String.format("d:\\temp\\%2$s",
                    today, "outgoingMail.csv"));
            PrintWriter out = new PrintWriter(outFile);
            List<OutgoingMailBean> outgoingMailBeans = this.getAllOutgoingMailByYear(today.get(Calendar.YEAR));
            out.println("UID;Исходящий номер;Описание письма;ФИО Ответственного;Номер в \"Documentum\";"
                    + "Компания-получатель;ФИО Получателя;Дата регистрации;Дата ожидаемого ответа");
            for (OutgoingMailBean outgoingMailBean : outgoingMailBeans) {
                out.println(String.format("%1$s;%2$s;%3$s;%4$s;%5$s;%6$s;%7$s;%8$s;%9$s;",
                        outgoingMailBean.getUid(),
                        outgoingMailBean.getOutgoingNumber(),
                        outgoingMailBean.getDescription(),
                        outgoingMailBean.getWorkflowUserBean().getLastName()+" "+
                        outgoingMailBean.getWorkflowUserBean().getFirstName()+" "+
                        outgoingMailBean.getWorkflowUserBean().getMiddleName(),
                        outgoingMailBean.getDocumentumNumber(),
                        outgoingMailBean.getReceiverCompany(),
                        outgoingMailBean.getReceiverName(),
                        outgoingMailBean.getOutgoingDate(),
                        outgoingMailBean.getDueDate()
                ));
            }
            out.close();
        } catch (ParseException ex) {
            Logger.getLogger(OutgoingFileServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<OutgoingMailBean> getByPrimaveraUid(String primaveraUid) {
        return outgoingMailDao.getByPrimaveraUid(primaveraUid);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<String> getAllOutgoingMailWithPrimaveraUid() {
        return outgoingMailDao.getAllOutgoingMailWithPrimaveraUid();
    }
}
