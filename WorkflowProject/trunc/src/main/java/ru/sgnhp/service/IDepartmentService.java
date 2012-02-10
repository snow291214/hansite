package ru.sgnhp.service;

import java.util.List;
import ru.sgnhp.domain.Department;

/**
 * ***
 *
 * @author Alexey Khudyakov @Skype: khudyakov.alexey
 *
 *****
 */
public interface IDepartmentService extends IGenericService<Department, Long> {

    @Override
    public List<Department> getAll();
}
