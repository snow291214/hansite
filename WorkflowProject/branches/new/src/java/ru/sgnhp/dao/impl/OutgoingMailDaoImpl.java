package ru.sgnhp.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sgnhp.dao.IOutgoingMailDao;
import ru.sgnhp.domain.OutgoingMailBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class OutgoingMailDaoImpl extends GenericDaoHibernate<OutgoingMailBean, Long> implements IOutgoingMailDao {

    public OutgoingMailDaoImpl() {
        super(OutgoingMailBean.class);
    }

    public OutgoingMailBean getByOutgoingNumber(Long outgoingNumber) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("outgoingNumber", outgoingNumber);
        List<OutgoingMailBean> list = this.findByNamedQuery("WorkflowBean.findByOutgoingNumber", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public List<OutgoingMailBean> getByOutgoingDate(Date outgoingDate) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("outgoingDate", outgoingDate);
        List<OutgoingMailBean> list = this.findByNamedQuery("WorkflowBean.findByOutgoingDate", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public OutgoingMailBean getByDocumentumNumber(String documentumNumber) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("documentumNumber", documentumNumber);
        List<OutgoingMailBean> list = this.findByNamedQuery("WorkflowBean.findByDocumentumNumber", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public List<OutgoingMailBean> getByReceiverCompany(String receiverCompany) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("receiverCompany", receiverCompany);
        List<OutgoingMailBean> list = this.findByNamedQuery("WorkflowBean.findByReceiverCompany", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public List<OutgoingMailBean> getByReceiverName(String receiverName) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("receiverName", receiverName);
        List<OutgoingMailBean> list = this.findByNamedQuery("WorkflowBean.findByReceiverName", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public List<OutgoingMailBean> getByResponsibleName(String responsibleName) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("responsibleName", responsibleName);
        List<OutgoingMailBean> list = this.findByNamedQuery("WorkflowBean.findByResponsibleName", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public List<OutgoingMailBean> getByDueDate(Date dueDate) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("dueDate", dueDate);
        List<OutgoingMailBean> list = this.findByNamedQuery("WorkflowBean.findByDueDate", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }
}
