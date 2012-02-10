package ru.sgnhp.service.impl;

import java.util.List;
import ru.sgnhp.dao.IDepartmentDao;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.domain.Department;
import ru.sgnhp.service.IDepartmentService;

/**
 *
 * Khudyakov Alexey Skype: khudyakov.alexey Email: khudyakov.alexey@gmail.com
 *
 */
public class DepartmentServiceImpl extends GenericServiceImpl<Department, Long> implements IDepartmentService {

    private IDepartmentDao departmentDao;
    
    public DepartmentServiceImpl(IGenericDao<Department, Long> genericDao) {
        super(genericDao);
    }

    @Override
    public List<Department> getAll(){
        return departmentDao.getAll();
    }
    
    /**
     * @return the departmentDao
     */
    public IDepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    /**
     * @param departmentDao the departmentDao to set
     */
    public void setDepartmentDao(IDepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }
}
