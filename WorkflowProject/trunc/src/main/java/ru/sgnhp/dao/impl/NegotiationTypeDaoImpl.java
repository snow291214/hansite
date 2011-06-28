package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.INegotiationTypeDao;
import ru.sgnhp.domain.NegotiationTypeBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class NegotiationTypeDaoImpl extends GenericDaoHibernate<NegotiationTypeBean, Long> implements INegotiationTypeDao {

    public NegotiationTypeDaoImpl() {
        super(NegotiationTypeBean.class);
    }
}
