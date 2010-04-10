package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IOutgoingMailDao;
import ru.sgnhp.entity.OutgoingMailBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class OutgoingMailDaoImpl extends GenericDaoHibernate<OutgoingMailBean, Long> implements IOutgoingMailDao {

    public OutgoingMailDaoImpl() {
        super(OutgoingMailBean.class);
    }
}
