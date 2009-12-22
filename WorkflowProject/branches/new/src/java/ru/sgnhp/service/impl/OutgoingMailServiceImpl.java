package ru.sgnhp.service.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.domain.OutgoingMailBean;
import ru.sgnhp.service.IOutgoingMailService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class OutgoingMailServiceImpl extends GenericServiceImpl<OutgoingMailBean, Long> implements IOutgoingMailService {

    public OutgoingMailServiceImpl(IGenericDao<OutgoingMailBean, Long> genericDao) {
        super(genericDao);
    }
}
