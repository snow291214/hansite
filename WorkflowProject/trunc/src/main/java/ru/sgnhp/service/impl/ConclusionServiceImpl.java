package ru.sgnhp.service.impl;

import java.util.List;
import ru.sgnhp.dao.IConclusionDao;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.domain.ConclusionBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IConclusionService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class ConclusionServiceImpl extends GenericServiceImpl<ConclusionBean, Long> implements IConclusionService {

    private IConclusionDao conclusionDao;

    public ConclusionServiceImpl(IGenericDao<ConclusionBean, Long> genericDao) {
        super(genericDao);
    }

    /**
     * @return the conclusionDao
     */
    public IConclusionDao getConclusionDao() {
        return conclusionDao;
    }

    /**
     * @param conclusionDao the conclusionDao to set
     */
    public void setConclusionDao(IConclusionDao conclusionDao) {
        this.conclusionDao = conclusionDao;
    }

    @Override
    public List<ConclusionBean> findActiveConclusionsByUser(WorkflowUserBean workflowUserBean) {
        return conclusionDao.findActiveConclusionsByUser(workflowUserBean);
    }
}
