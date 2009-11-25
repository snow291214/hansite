package ru.sgnhp.dao;

import ru.sgnhp.domain.StateBean;

public interface IStateDao {
    StateBean getStateByStateUid(int stateUid);
}
