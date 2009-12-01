package ru.sgnhp.service.impl;

import org.springframework.transaction.annotation.Transactional;
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
@Transactional(readOnly = true)
public class UploadManagerServiceImpl extends GenericServiceImpl<FileBean, Long> implements IUploadManagerService{

    public UploadManagerServiceImpl(IGenericDao<FileBean, Long> genericDao) {
        super(genericDao);
    }
}
