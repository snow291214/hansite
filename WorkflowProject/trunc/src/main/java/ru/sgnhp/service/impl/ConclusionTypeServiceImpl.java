package ru.sgnhp.service.impl;

import ru.sgnhp.dao.IConclusionTypeDao;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.domain.ConclusionTypeBean;
import ru.sgnhp.service.IConclusionTypeService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class ConclusionTypeServiceImpl extends GenericServiceImpl<ConclusionTypeBean, Long> implements IConclusionTypeService {

    private IConclusionTypeDao conclusionTypeDao;

    public ConclusionTypeServiceImpl(IGenericDao<ConclusionTypeBean, Long> genericDao) {
        super(genericDao);
    }

    /**
     * @return the conclusionTypeDao
     */
    public IConclusionTypeDao getConclusionTypeDao() {
        return conclusionTypeDao;
    }

    /**
     * @param conclusionTypeDao the conclusionTypeDao to set
     */
    public void setConclusionTypeDao(IConclusionTypeDao conclusionTypeDao) {
        this.conclusionTypeDao = conclusionTypeDao;
    }
}
