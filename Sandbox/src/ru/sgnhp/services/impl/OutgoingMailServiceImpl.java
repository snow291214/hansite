package ru.sgnhp.services.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.entity.OutgoingMailBean;
import ru.sgnhp.services.IOutgoingMailService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class OutgoingMailServiceImpl extends GenericService<OutgoingMailBean, Long> implements IOutgoingMailService {

    public OutgoingMailServiceImpl(IGenericDao<OutgoingMailBean, Long> genericDao) {
        super(genericDao);
    }
}
