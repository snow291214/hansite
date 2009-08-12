package ru.sgnhp.service.impl;

import java.util.List;
import ru.sgnhp.dao.IUploadDao;
import ru.sgnhp.domain.FileUploadBean;
import ru.sgnhp.service.IUploadManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class UploadManagerServiceImpl implements IUploadManagerService{

    private IUploadDao uploadDao;

    public void saveFile(FileUploadBean bean) {
        getUploadDao().saveFile(bean);
    }

    public List<FileUploadBean> getFileUploadBeanByTaskUid(Long taskUid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public IUploadDao getUploadDao() {
        return uploadDao;
    }

    public void setUploadDao(IUploadDao uploadDao) {
        this.uploadDao = uploadDao;
    }

}
