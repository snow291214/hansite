package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IDocumentFileDao;
import ru.sgnhp.domain.DocumentFileBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class DocumentFileDaoImpl extends GenericDaoHibernate<DocumentFileBean, Long> implements IDocumentFileDao {

    public DocumentFileDaoImpl() {
        super(DocumentFileBean.class);
    }
}
