package ru.sgnhp.service;

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
public interface IConclusionService extends IGenericService<ConclusionBean, Long> {

    List<ConclusionBean> findActiveConclusionsByUser(WorkflowUserBean workflowUserBean);
}
