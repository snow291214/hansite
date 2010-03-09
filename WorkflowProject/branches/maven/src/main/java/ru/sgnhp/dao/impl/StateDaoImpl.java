package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IStateDao;
import ru.sgnhp.domain.StateBean;

public class StateDaoImpl extends GenericDaoHibernate<StateBean, Long> implements IStateDao {

    public StateDaoImpl() {
        super(StateBean.class);
    }
}
