package ru.sgnhp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sgnhp.dao.INegotiationDao;
import ru.sgnhp.domain.NegotiationBean;
import ru.sgnhp.domain.WorkflowUserBean;

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

    @Override
    public List<NegotiationBean> findNegotiationsByUser(WorkflowUserBean workflowUserBean) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("workflowUserBean", workflowUserBean);
        List<NegotiationBean> list = this.findByNamedQuery("NegotiationBean.findNegotiationsByUser", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }
}
