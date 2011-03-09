package ru.sgnhp.service.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.IUserGroupDao;
import ru.sgnhp.domain.UserGroupBean;
import ru.sgnhp.service.IUserGroupService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class UserGroupServiceImpl extends GenericServiceImpl<UserGroupBean, Long> implements IUserGroupService {

    private IUserGroupDao userGroupDao;

    public UserGroupServiceImpl(IGenericDao<UserGroupBean, Long> genericDao) {
        super(genericDao);
    }

    public IUserGroupDao getUserGroupDao() {
        return userGroupDao;
    }

    public void setUserGroupDao(IUserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }
}
