package ru.sgnhp.services.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.entity.Tasks;
import ru.sgnhp.services.ITasksService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class TasksService extends GenericService<Tasks, Long> implements ITasksService{

    public TasksService(IGenericDao<Tasks, Long> genericDao) {
        super(genericDao);
    }

}
