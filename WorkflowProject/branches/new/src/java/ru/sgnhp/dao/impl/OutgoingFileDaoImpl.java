package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IOutgoingFileDao;
import ru.sgnhp.domain.OutgoingFileBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class OutgoingFileDaoImpl extends GenericDaoHibernate<OutgoingFileBean, Long> implements IOutgoingFileDao {

    public OutgoingFileDaoImpl() {
        super(OutgoingFileBean.class);
    }
}
