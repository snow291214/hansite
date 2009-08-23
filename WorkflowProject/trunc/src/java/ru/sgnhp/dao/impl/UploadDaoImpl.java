package ru.sgnhp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
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
    private static String SelectPart = "SELECT files.Uid, files.FileName FROM  files";
    private static String SelectAll = "SELECT files.Uid, files.FileName FROM  files";

    public void saveFile(FileUploadBean bean) {
        getSimpleJdbcTemplate().update(INSERT, bean.getTaskUid(), bean.getFileName(), bean.getContentStream());
    }

    public List<FileUploadBean> getFileUploadBeanByTaskUid(Long taskUid) {
        return getSimpleJdbcTemplate().query(SelectPart+" Where files.taskUid = ?", new FilesMapper(), taskUid);
    }

    private static class FilesMapper implements ParameterizedRowMapper<FileUploadBean> {

        public FileUploadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
            FileUploadBean bean = new FileUploadBean();
            bean.setUid(rs.getLong("Uid"));
            bean.setFileName(rs.getString("FileName"));
            return bean;
        }

    }
}
