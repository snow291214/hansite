package ru.sgnhp.services.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.entity.Groups;
import ru.sgnhp.services.IGroupsService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class GroupsService extends GenericService<Groups, Long> implements IGroupsService {

    public GroupsService(IGenericDao<Groups, Long> genericDao) {
        super(genericDao);
    }
}
