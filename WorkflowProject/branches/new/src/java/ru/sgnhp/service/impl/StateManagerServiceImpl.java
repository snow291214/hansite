package ru.sgnhp.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.IStateDao;
import ru.sgnhp.domain.StateBean;
import ru.sgnhp.service.IStateManagerService;

@Transactional(readOnly = true)
public class StateManagerServiceImpl extends GenericServiceImpl<StateBean, Long> implements IStateManagerService {

    private IStateDao stateDao;

    public StateManagerServiceImpl(IGenericDao<StateBean, Long> genericDao) {
        super(genericDao);
    }

    public void setStateDao(IStateDao stateDao) {
        this.stateDao = stateDao;
    }
}
