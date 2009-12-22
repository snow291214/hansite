package ru.sgnhp.service.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.domain.OutgoingFileBean;
import ru.sgnhp.service.IOutgoingFileService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class OutgoingFileServiceImpl extends GenericServiceImpl<OutgoingFileBean, Long> implements IOutgoingFileService {

    public OutgoingFileServiceImpl(IGenericDao<OutgoingFileBean, Long> genericDao) {
        super(genericDao);
    }
}
