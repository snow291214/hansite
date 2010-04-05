package ru.sgnhp.dao;

import java.util.Date;
import java.util.List;
import ru.sgnhp.entity.DocumentBean;
import ru.sgnhp.entity.DocumentTypeBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IDocumentDao extends IGenericDao<DocumentBean, Long> {

    public List<DocumentBean> findByDocumentNumber(Long documentNumber, DocumentTypeBean documentTypeBean);

    public List<DocumentBean> findByDocumentDate(Date startDate, Date finishDate, DocumentTypeBean documentTypeBean);

    public int getNewDocumentNumber(Integer currentYear, Long documentTypeUid);
}
