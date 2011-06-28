package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.INegotiationDao;
import ru.sgnhp.domain.NegotiationBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class NegotiationDaoImpl extends GenericDaoHibernate<NegotiationBean, Long> implements INegotiationDao {

    public NegotiationDaoImpl() {
        super(NegotiationBean.class);
    }
}
