package ru.sgnhp.service.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.INegotiationTypeDao;
import ru.sgnhp.domain.NegotiationTypeBean;
import ru.sgnhp.service.INegotiationTypeService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class NegotiationTypeServiceImpl extends GenericServiceImpl<NegotiationTypeBean, Long> implements INegotiationTypeService {

    private INegotiationTypeDao negotiationTypeDao;

    public NegotiationTypeServiceImpl(IGenericDao<NegotiationTypeBean, Long> genericDao) {
        super(genericDao);
    }

    /**
     * @return the negotiationTypeDao
     */
    public INegotiationTypeDao getNegotiationTypeDao() {
        return negotiationTypeDao;
    }

    /**
     * @param negotiationTypeDao the negotiationTypeDao to set
     */
    public void setNegotiationTypeDao(INegotiationTypeDao negotiationTypeDao) {
        this.negotiationTypeDao = negotiationTypeDao;
    }
}
