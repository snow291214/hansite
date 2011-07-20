package ru.sgnhp.dao;

import java.util.List;
import ru.sgnhp.domain.NegotiationBean;
import ru.sgnhp.domain.WorkflowUserBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public interface INegotiationDao extends IGenericDao<NegotiationBean, Long> {

    List<NegotiationBean> findNegotiationsByUser(WorkflowUserBean workflowUserBean);
}
