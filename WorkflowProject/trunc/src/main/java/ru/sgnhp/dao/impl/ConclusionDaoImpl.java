package ru.sgnhp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sgnhp.dao.IConclusionDao;
import ru.sgnhp.domain.ConclusionBean;
import ru.sgnhp.domain.WorkflowUserBean;

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

    @Override
    public List<ConclusionBean> findActiveConclusionsByUser(WorkflowUserBean workflowUserBean) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("workflowUserBean", workflowUserBean);
        List<ConclusionBean> list = this.findByNamedQuery("ConclusionBean.findActiveConclusionsByUser", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }
}
