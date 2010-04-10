package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IDocumentFileDao;
import ru.sgnhp.entity.DocumentFileBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class DocumentFileDaoImpl extends GenericDaoHibernate<DocumentFileBean, Long> implements IDocumentFileDao {

    public DocumentFileDaoImpl() {
        super(DocumentFileBean.class);
    }
}
