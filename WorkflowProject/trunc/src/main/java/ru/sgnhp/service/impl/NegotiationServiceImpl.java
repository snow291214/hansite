package ru.sgnhp.service.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.INegotiationDao;
import ru.sgnhp.domain.NegotiationBean;
import ru.sgnhp.service.INegotiationService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class NegotiationServiceImpl  extends GenericServiceImpl<NegotiationBean, Long> implements INegotiationService {

    private INegotiationDao negotiationDao;

    public NegotiationServiceImpl(IGenericDao<NegotiationBean, Long> genericDao) {
        super(genericDao);
    }

    /**
     * @return the negotiationDao
     */
    public INegotiationDao getNegotiationDao() {
        return negotiationDao;
    }

    /**
     * @param negotiationDao the negotiationDao to set
     */
    public void setNegotiationDao(INegotiationDao negotiationDao) {
        this.negotiationDao = negotiationDao;
    }
}
