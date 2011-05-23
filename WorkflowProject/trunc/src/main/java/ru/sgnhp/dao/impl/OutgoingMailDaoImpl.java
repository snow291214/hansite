package ru.sgnhp.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sgnhp.dao.IOutgoingMailDao;
import ru.sgnhp.domain.OutgoingMailBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class OutgoingMailDaoImpl extends GenericDaoHibernate<OutgoingMailBean, Long> implements IOutgoingMailDao {

    public OutgoingMailDaoImpl() {
        super(OutgoingMailBean.class);
    }

    @Override
    public List<OutgoingMailBean> getByOutgoingNumber(Long outgoingNumber) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("outgoingNumber", outgoingNumber);
        List<OutgoingMailBean> list = this.findByNamedQuery("OutgoingMailBean.findByOutgoingNumber", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<OutgoingMailBean> getByDescription(String description) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("description", description);
        List<OutgoingMailBean> list = this.findByNamedQuery("OutgoingMailBean.findByDescription", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<OutgoingMailBean> getByOutgoingDate(Date outgoingDate) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("outgoingDate", outgoingDate);
        List<OutgoingMailBean> list = this.findByNamedQuery("OutgoingMailBean.findByOutgoingDate", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<OutgoingMailBean> getByDocumentumNumber(String documentumNumber) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("documentumNumber", documentumNumber);
        List<OutgoingMailBean> list = this.findByNamedQuery("OutgoingMailBean.findByDocumentumNumber", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<OutgoingMailBean> getByReceiverCompany(String receiverCompany) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("receiverCompany", receiverCompany);
        List<OutgoingMailBean> list = this.findByNamedQuery("OutgoingMailBean.findByReceiverCompany", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<OutgoingMailBean> getByReceiverName(String receiverName) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("receiverName", receiverName);
        List<OutgoingMailBean> list = this.findByNamedQuery("OutgoingMailBean.findByReceiverName", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<OutgoingMailBean> getByResponsibleUid(Long responsibleUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("responsibleUid", responsibleUid);
        List<OutgoingMailBean> list = this.findByNamedQuery("OutgoingMailBean.findByResponsibleUid", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<OutgoingMailBean> getByDueDate(Date dueDate) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("dueDate", dueDate);
        List<OutgoingMailBean> list = this.findByNamedQuery("OutgoingMailBean.findByDueDate",
                value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public Long getNewOutgoingNumber() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        List list = getSession().createQuery(String.format("SELECT Max(m.outgoingNumber) " +
                "FROM OutgoingMailBean m where m.outgoingDate BETWEEN '%1$s-01-01' AND '%1$s-12-31'",
                year)).list();
        if (list.get(0) instanceof Long) {
            return (Long) list.get(0);
        }
        return -1L;
    }

    @Override
    public List<OutgoingMailBean> getByPeriodOfDate(Date outgoingDate, Date dueDate) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("outgoingDate", outgoingDate);
        value.put("dueDate", dueDate);
        List<OutgoingMailBean> list = this.findByNamedQuery("OutgoingMailBean.findByPeriodOfDate",
                value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<OutgoingMailBean> getAllIncomingMailByYear(Integer currentYear) throws ParseException {
        Map<String, Object> value = new HashMap<String, Object>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date startDate = simpleDateFormat.parse("01.01."+currentYear.toString());
        Date finishDate = simpleDateFormat.parse("31.12."+currentYear.toString());
        value.put("startDate", startDate);
        value.put("finishDate", finishDate);
        List<OutgoingMailBean> list = this.findByNamedQuery("OutgoingMailBean.findAllOutgoingByYear",
                value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<OutgoingMailBean> getByPrimaveraUid(String primaveraUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("primaveraUid", primaveraUid);
        List<OutgoingMailBean> list = this.findByNamedQuery("OutgoingMailBean.findByPrimaveraUid",
                value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<String> getAllOutgoingMailWithPrimaveraUid() {
        List list = getSession().createQuery("SELECT distinct m.primaveraUid " +
                "FROM OutgoingMailBean m where m.primaveraUid <>''").list();
        return list;
    }
}
