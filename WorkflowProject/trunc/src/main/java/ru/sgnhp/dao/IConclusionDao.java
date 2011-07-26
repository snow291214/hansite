package ru.sgnhp.dao;

import java.util.List;
import ru.sgnhp.domain.ConclusionBean;
import ru.sgnhp.domain.WorkflowUserBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public interface IConclusionDao extends IGenericDao<ConclusionBean, Long> {

    List<ConclusionBean> findActiveConclusionsByUser(WorkflowUserBean workflowUserBean);
}
