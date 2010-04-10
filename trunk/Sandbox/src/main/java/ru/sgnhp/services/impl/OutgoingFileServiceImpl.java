package ru.sgnhp.services.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.entity.OutgoingFileBean;
import ru.sgnhp.services.IOutgoingFileService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class OutgoingFileServiceImpl extends GenericService<OutgoingFileBean, Long> implements IOutgoingFileService {

    public OutgoingFileServiceImpl(IGenericDao<OutgoingFileBean, Long> genericDao) {
        super(genericDao);
    }
}
