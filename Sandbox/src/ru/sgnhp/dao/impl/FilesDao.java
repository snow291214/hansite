package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IFilesDao;
import ru.sgnhp.entity.Files;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class FilesDao extends GenericDaoHibernate<Files, Long> implements IFilesDao {

    public FilesDao() {
        super(Files.class);
    }
}
