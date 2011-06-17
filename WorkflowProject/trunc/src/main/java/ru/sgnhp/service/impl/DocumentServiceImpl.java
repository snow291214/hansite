package ru.sgnhp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.dao.IDocumentDao;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.domain.DocumentBean;
import ru.sgnhp.domain.DocumentTypeBean;
import ru.sgnhp.service.IDocumentService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class DocumentServiceImpl extends GenericServiceImpl<DocumentBean, Long> implements IDocumentService {

    private IDocumentDao documentDao;

    public DocumentServiceImpl(IGenericDao<DocumentBean, Long> genericDao) {
        super(genericDao);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public List<DocumentBean> findByDocumentNumber(Long documentNumber, DocumentTypeBean documentTypeBean) {
        return documentDao.findByDocumentNumber(documentNumber, documentTypeBean);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public List<DocumentBean> findByDocumentDate(Date startDate, Date finishDate, DocumentTypeBean documentTypeBean) {
        return documentDao.findByDocumentDate(startDate, finishDate, documentTypeBean);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public int getNewDocumentNumber(int currentYear, Long documentTypeUid) {
        return documentDao.getNewDocumentNumber(currentYear, documentTypeUid);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public List<DocumentBean> getAllDocumentsByYear(Integer currentYear, DocumentTypeBean documentTypeBean) throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date startDate = simpleDateFormat.parse("01.01." + currentYear.toString());
        Date finishDate = simpleDateFormat.parse("31.12." + currentYear.toString());
        return this.findByDocumentDate(startDate, finishDate, documentTypeBean);
    }

    public void setDocumentDao(IDocumentDao documentDao) {
        this.documentDao = documentDao;
    }
}
