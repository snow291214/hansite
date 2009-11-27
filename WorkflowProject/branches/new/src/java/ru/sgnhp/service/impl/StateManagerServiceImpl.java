package ru.sgnhp.service.impl;

import ru.sgnhp.dao.IStateDao;
import ru.sgnhp.domain.StateBean;
import ru.sgnhp.service.IStateManagerService;

public class StateManagerServiceImpl implements IStateManagerService{

    private IStateDao stateDao;

    public StateBean getStateByStateUid(int stateUid) {
        return stateDao.getStateByStateUid(stateUid);
    }

    public void setStateDao(IStateDao stateDao) {
        this.stateDao = stateDao;
    }

}
