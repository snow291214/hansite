package ru.sgnhp.services.impl;

import java.util.List;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.IOutgoingFileDao;
import ru.sgnhp.entity.OutgoingFileBean;
import ru.sgnhp.entity.OutgoingMailBean;
import ru.sgnhp.services.IOutgoingFileService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class OutgoingFileServiceImpl extends GenericService<OutgoingFileBean, Long> implements IOutgoingFileService {

    private IOutgoingFileDao outgoingFileDao;

    public OutgoingFileServiceImpl(IGenericDao<OutgoingFileBean, Long> genericDao) {
        super(genericDao);
    }

    @Override
    public List<OutgoingFileBean> getFilesByOutgoingMail(OutgoingMailBean outgoingMailBean) {
        return getOutgoingFileDao().getFilesByOutgoingMail(outgoingMailBean);
    }

    public IOutgoingFileDao getOutgoingFileDao() {
        return outgoingFileDao;
    }

    public void setOutgoingFileDao(IOutgoingFileDao outgoingFileDao) {
        this.outgoingFileDao = outgoingFileDao;
    }
}
