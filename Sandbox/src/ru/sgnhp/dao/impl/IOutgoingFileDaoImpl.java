package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IOutgoingFileDao;
import ru.sgnhp.entity.OutgoingFileBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class IOutgoingFileDaoImpl extends GenericDaoHibernate<OutgoingFileBean, Long> implements IOutgoingFileDao {

    public IOutgoingFileDaoImpl() {
        super(OutgoingFileBean.class);
    }
}
