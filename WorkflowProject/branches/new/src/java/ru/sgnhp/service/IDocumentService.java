package ru.sgnhp.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import ru.sgnhp.domain.DocumentBean;
import ru.sgnhp.domain.DocumentTypeBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IDocumentService extends IGenericService<DocumentBean, Long> {

    public List<DocumentBean> findByDocumentNumber(Long documentNumber, DocumentTypeBean documentTypeBean);

    public List<DocumentBean> findByDocumentDate(Date startDate, Date finishDate, DocumentTypeBean documentTypeBean);

    List<DocumentBean> getAllDocumentsByYear(Integer currentYear, DocumentTypeBean documentTypeBean) throws ParseException;

    public int getNewDocumentNumber(int currentYear, Long documentTypeUid);
}
