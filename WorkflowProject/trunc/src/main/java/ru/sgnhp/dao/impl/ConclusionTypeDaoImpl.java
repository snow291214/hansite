package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IConclusionTypeDao;
import ru.sgnhp.domain.ConclusionTypeBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class ConclusionTypeDaoImpl extends GenericDaoHibernate<ConclusionTypeBean, Long> implements IConclusionTypeDao {

    public ConclusionTypeDaoImpl() {
        super(ConclusionTypeBean.class);
    }
}
