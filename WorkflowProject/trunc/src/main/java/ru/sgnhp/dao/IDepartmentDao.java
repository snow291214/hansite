package ru.sgnhp.dao;

import java.util.List;
import ru.sgnhp.domain.Department;

/**
 * ***
 *
 * @author Alexey Khudyakov @Skype: khudyakov.alexey
 *
 *****
 */
public interface IDepartmentDao extends IGenericDao<Department, Long> {

    @Override
    public List<Department> getAll();
}
