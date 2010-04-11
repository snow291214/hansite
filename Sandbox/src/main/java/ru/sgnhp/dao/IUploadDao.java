package ru.sgnhp.dao;

import java.util.List;
import ru.sgnhp.entity.FileBean;
import ru.sgnhp.entity.TaskBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IUploadDao extends IGenericDao<FileBean, Long> {

    //List<FileBean> getFileBeansByTaskUid(Long taskUid);
    //FileUploadBean getFileUploadBeanByUid(Long fileUploadBeanUid);
    public List<FileBean> getFilesByTask(TaskBean taskBean);
}
