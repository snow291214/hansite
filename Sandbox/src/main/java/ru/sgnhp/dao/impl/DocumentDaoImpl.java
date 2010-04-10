package ru.sgnhp.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sgnhp.dao.IDocumentDao;
import ru.sgnhp.entity.DocumentBean;
import ru.sgnhp.entity.DocumentTypeBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class DocumentDaoImpl extends GenericDaoHibernate<DocumentBean, Long> implements IDocumentDao {

    public DocumentDaoImpl() {
        super(DocumentBean.class);
    }

    public List<DocumentBean> findByDocumentNumber(Long documentNumber, DocumentTypeBean documentTypeBean) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("documentTypeBean", documentTypeBean);
        value.put("documentNumber", documentNumber);
        List<DocumentBean> list = this.findByNamedQuery("DocumentBean.findByDocumentNumber", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public List<DocumentBean> findByDocumentDate(Date startDate, Date finishDate, DocumentTypeBean documentTypeBean) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("documentTypeBean", documentTypeBean);
        value.put("startDate", startDate);
        value.put("finishDate", finishDate);
        List<DocumentBean> list = this.findByNamedQuery("DocumentBean.findByDocumentDate", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public int getNewDocumentNumber(Integer currentYear, Long documentTypeUid) {
        int result = 1;
        List list = getSession().createQuery(String.format("SELECT Max(m.documentNumber) "
                + "FROM DocumentBean m where m.documentDate BETWEEN '%1$s-01-01' AND '%1$s-12-31' "
                + "AND m.documentTypeBean.uid = %2$d",
                currentYear, documentTypeUid)).list();
        if (list.get(0) instanceof Integer) {
            result = (Integer) list.get(0) + 1;
            return result;
        }
        return result;
    }
}
