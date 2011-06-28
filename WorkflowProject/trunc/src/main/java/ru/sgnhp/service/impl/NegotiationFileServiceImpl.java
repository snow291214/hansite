package ru.sgnhp.service.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.INegotiationFileDao;
import ru.sgnhp.domain.NegotiationFileBean;
import ru.sgnhp.service.INegotiationFileService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class NegotiationFileServiceImpl  extends GenericServiceImpl<NegotiationFileBean, Long> implements INegotiationFileService {

    private INegotiationFileDao negotiationFileDao;

    public NegotiationFileServiceImpl(IGenericDao<NegotiationFileBean, Long> genericDao) {
        super(genericDao);
    }

    /**
     * @return the negotiationFileDao
     */
    public INegotiationFileDao getNegotiationFileDao() {
        return negotiationFileDao;
    }

    /**
     * @param negotiationFileDao the negotiationFileDao to set
     */
    public void setNegotiationFileDao(INegotiationFileDao negotiationFileDao) {
        this.negotiationFileDao = negotiationFileDao;
    }
}
