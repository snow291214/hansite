package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IConclusionDao;
import ru.sgnhp.domain.ConclusionBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class ConclusionDaoImpl extends GenericDaoHibernate<ConclusionBean, Long> implements IConclusionDao {

    public ConclusionDaoImpl() {
        super(ConclusionBean.class);
    }
}
