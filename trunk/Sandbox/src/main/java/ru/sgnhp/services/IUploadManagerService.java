package ru.sgnhp.services;

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
public interface IUploadManagerService extends IGenericService<FileBean, Long>{
    public List<FileBean> getFilesByTaskUid(TaskBean taskBean);
}
