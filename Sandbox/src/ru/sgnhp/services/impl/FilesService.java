package ru.sgnhp.services.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.entity.Files;
import ru.sgnhp.services.IFilesService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class FilesService  extends GenericService<Files, Long> implements IFilesService{

    public FilesService(IGenericDao<Files, Long> genericDao) {
        super(genericDao);
    }
}
