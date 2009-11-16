package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.ITaskDao;
import ru.sgnhp.entity.Tasks;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class TasksDao extends GenericDaoHibernate<Tasks, Long> implements ITaskDao {

    public TasksDao() {
        super(Tasks.class);
    }
}
