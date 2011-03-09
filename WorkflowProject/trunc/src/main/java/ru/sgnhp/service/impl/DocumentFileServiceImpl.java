package ru.sgnhp.service.impl;

import ru.sgnhp.dao.IDocumentFileDao;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.domain.DocumentFileBean;
import ru.sgnhp.service.IDocumentFileService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class DocumentFileServiceImpl extends GenericServiceImpl<DocumentFileBean, Long> implements IDocumentFileService {

    private IDocumentFileDao documentFileDao;

    public DocumentFileServiceImpl(IGenericDao<DocumentFileBean, Long> genericDao) {
        super(genericDao);
    }

    public void setDocumentFileDao(IDocumentFileDao documentFileDao) {
        this.documentFileDao = documentFileDao;
    }
}
