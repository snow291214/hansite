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
public class UploadDaoImpl extends SimpleJdbcDaoSupport implements IUploadDao {

    private static String INSERT = "Insert Into files(`TaskUid`,`FileName`,`Blob`) Values(?,?,?)";
    private static String SelectPart = "SELECT files.Uid, files.FileName FROM  files";
    private static String SelectAll = "SELECT * FROM  files";

    public void saveFile(FileUploadBean bean) {
        getSimpleJdbcTemplate().update(INSERT, bean.getTaskUid(), bean.getFileName(), bean.getContentStream());
    }

    public List<FileUploadBean> getFileUploadBeanByTaskUid(Long taskUid) {
        return getSimpleJdbcTemplate().query(SelectPart + " Where files.TaskUid = ?", new FilesMapper(), taskUid);
    }

    public FileUploadBean getFileUploadBeanByUid(Long fileUploadBeanUid) {
        List<FileUploadBean> file = getSimpleJdbcTemplate().query(SelectAll + " Where files.Uid = ?", new FilesMapperEx(), fileUploadBeanUid);
        if (file.size() > 0) {
            return (FileUploadBean) file.toArray()[0];
        } else {
            return null;
        }
    }

    private static class FilesMapper implements ParameterizedRowMapper<FileUploadBean> {

        public FileUploadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
            FileUploadBean bean = new FileUploadBean();
            bean.setUid(rs.getLong("Uid"));
            bean.setFileName(rs.getString("FileName"));
            return bean;
        }
    }

    private static class FilesMapperEx extends FilesMapper {

        @Override
        public FileUploadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
            FileUploadBean bean = new FileUploadBean();
            bean.setUid(rs.getLong("Uid"));
            bean.setFileName(rs.getString("FileName"));
            bean.setBlob(rs.getBlob("Blob"));
            return bean;
        }
    }
}
