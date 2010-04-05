package ru.sgnhp.services.impl;

import ru.sgnhp.dao.IDocumentFileDao;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.entity.DocumentFileBean;
import ru.sgnhp.services.IDocumentFileService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class DocumentFileServiceImpl extends GenericService<DocumentFileBean, Long> implements IDocumentFileService {

    private IDocumentFileDao documentFileDao;

    public DocumentFileServiceImpl(IGenericDao<DocumentFileBean, Long> genericDao) {
        super(genericDao);
    }

    public void setDocumentFileDao(IDocumentFileDao documentFileDao) {
        this.documentFileDao = documentFileDao;
    }
}
