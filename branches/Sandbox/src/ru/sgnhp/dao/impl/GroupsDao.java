package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IGroupsDao;
import ru.sgnhp.entity.Groups;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class GroupsDao extends GenericDaoHibernate<Groups, Long> implements IGroupsDao {

    public GroupsDao() {
        super(Groups.class);
    }
}
