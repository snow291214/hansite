package ru.sgnhp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import ru.sgnhp.dao.IStateDao;
import ru.sgnhp.domain.StateBean;

public class StateDaoImpl extends SimpleJdbcDaoSupport implements IStateDao{

    private static String SELECT = "SELECT * FROM state";
    
    public StateBean getStateByStateUid(int stateUid) {
        List<StateBean> beans = getSimpleJdbcTemplate().query(SELECT +
                " WHERE  state.StateUid = ?", new StateMapper(), stateUid);
        if (beans.size() > 0) {
            return (StateBean) beans.toArray()[0];
        } else {
            return null;
        }
    }

    private static class StateMapper implements ParameterizedRowMapper<StateBean> {

        public StateBean mapRow(ResultSet rs, int rowNum) throws SQLException {
            StateBean bean = new StateBean();
            bean.setStateUid(rs.getLong("StateUid"));
            bean.setStateDescription(rs.getString("StateDescription"));
            return bean;
        }

    }
}
