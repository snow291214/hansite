package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IUploadDao;
import ru.sgnhp.domain.FileBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class UploadDaoImpl extends GenericDaoHibernate<FileBean, Long> implements IUploadDao {

    public UploadDaoImpl() {
        super(FileBean.class);
    }
}
