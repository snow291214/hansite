package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IUsersDao;
import ru.sgnhp.entity.Users;

public class UsersDao extends GenericDaoHibernate<Users, Long> implements IUsersDao {

    public UsersDao() {
        super(Users.class);
    }
}
