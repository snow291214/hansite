package ru.sgnhp.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import ru.sgnhp.dao.IUploadDao;
import ru.sgnhp.domain.FileUploadBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class UploadDaoImpl extends SimpleJdbcDaoSupport implements IUploadDao{
    
    private static String INSERT = "Insert Into files(`TaskUid`,`FileName`,`Blob`) Values(?,?,?)";

    public void saveFile(FileUploadBean bean) {
        getSimpleJdbcTemplate().update(INSERT, bean.getTaskUid(), bean.getFileName(), bean.getContentStream());
    }

    public List<FileUploadBean> getFileUploadBeanByTaskUid(Long taskUid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
