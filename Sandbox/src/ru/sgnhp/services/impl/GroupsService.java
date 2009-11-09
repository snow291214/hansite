/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.services.impl;

import ru.sgnhp.dao.IGroupsDao;
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

    public GroupsService(IGroupsDao groupsDao) {
        super(groupsDao);
    }
}
