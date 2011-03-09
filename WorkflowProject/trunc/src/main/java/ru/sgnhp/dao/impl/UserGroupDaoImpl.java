package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IUserGroupDao;
import ru.sgnhp.domain.UserGroupBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class UserGroupDaoImpl extends GenericDaoHibernate<UserGroupBean, Long> implements IUserGroupDao{

    public UserGroupDaoImpl() {
        super(UserGroupBean.class);
    }
}
