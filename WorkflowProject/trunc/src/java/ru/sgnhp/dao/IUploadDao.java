package ru.sgnhp.dao;

import java.util.List;
import ru.sgnhp.domain.FileUploadBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IUploadDao {

    void saveFile (FileUploadBean bean);

    List<FileUploadBean> getFileUploadBeanByTaskUid(Long taskUid);

    FileUploadBean getFileUploadBeanByUid(Long fileUploadBeanUid);
}
