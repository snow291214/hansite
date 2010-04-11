package ru.sgnhp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.sgnhp.dao.IUploadDao;
import ru.sgnhp.entity.FileBean;
import ru.sgnhp.entity.TaskBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class UploadDaoImpl extends GenericDaoHibernate<FileBean, Long> implements IUploadDao {

    public UploadDaoImpl() {
        super(FileBean.class);
    }

    @Override
    public List<FileBean> getFilesByTask(TaskBean taskBean) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("tasks", taskBean);
        List<FileBean> list = this.findByNamedQuery("FileBean.findByTask", value);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

}
