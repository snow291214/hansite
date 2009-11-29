package ru.sgnhp.service.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.domain.FileBean;
import ru.sgnhp.service.IUploadManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class UploadManagerServiceImpl extends GenericServiceImpl<FileBean, Long> implements IUploadManagerService{

    public UploadManagerServiceImpl(IGenericDao<FileBean, Long> genericDao) {
        super(genericDao);
    }
}
