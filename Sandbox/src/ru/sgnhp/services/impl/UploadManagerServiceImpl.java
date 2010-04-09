package ru.sgnhp.services.impl;

import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.IUploadDao;

import ru.sgnhp.entity.FileBean;

import ru.sgnhp.services.IUploadManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
@Transactional(readOnly = true)
public class UploadManagerServiceImpl extends GenericService<FileBean, Long> implements IUploadManagerService {

    private IUploadDao uploadDao;

    public UploadManagerServiceImpl(IGenericDao<FileBean, Long> genericDao) {
        super(genericDao);
    }

    public IUploadDao getUploadDao() {
        return uploadDao;
    }

    public void setUploadDao(IUploadDao uploadDao) {
        this.uploadDao = uploadDao;
    }
}
