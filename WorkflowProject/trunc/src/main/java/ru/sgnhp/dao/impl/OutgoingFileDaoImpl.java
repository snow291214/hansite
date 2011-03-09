package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IOutgoingFileDao;
import ru.sgnhp.domain.OutgoingFileBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class OutgoingFileDaoImpl extends GenericDaoHibernate<OutgoingFileBean, Long> implements IOutgoingFileDao {

    public OutgoingFileDaoImpl() {
        super(OutgoingFileBean.class);
    }
}
