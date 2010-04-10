package ru.sgnhp.services.impl;

import ru.sgnhp.dao.IDocumentTypeDao;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.entity.DocumentTypeBean;
import ru.sgnhp.services.IDocumentTypeService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class DocumentTypeServiceImpl extends GenericService<DocumentTypeBean, Long> implements IDocumentTypeService {

    private IDocumentTypeDao documentTypeDao;

    public DocumentTypeServiceImpl(IGenericDao<DocumentTypeBean, Long> genericDao) {
        super(genericDao);
    }

    public IDocumentTypeDao getDocumentTypeDao() {
        return documentTypeDao;
    }

    public void setDocumentTypeDao(IDocumentTypeDao documentTypeDao) {
        this.documentTypeDao = documentTypeDao;
    }
}
