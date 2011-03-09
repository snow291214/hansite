package ru.sgnhp.service.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.IOutgoingFileDao;
import ru.sgnhp.domain.OutgoingFileBean;
import ru.sgnhp.service.IOutgoingFileService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class OutgoingFileServiceImpl extends GenericServiceImpl<OutgoingFileBean, Long> implements IOutgoingFileService {

    private IOutgoingFileDao outgoingFileDao;

    public OutgoingFileServiceImpl(IGenericDao<OutgoingFileBean, Long> genericDao) {
        super(genericDao);
    }

    public void setOutgoingFileDao(IOutgoingFileDao outgoingFileDao) {
        this.outgoingFileDao = outgoingFileDao;
    }
}
