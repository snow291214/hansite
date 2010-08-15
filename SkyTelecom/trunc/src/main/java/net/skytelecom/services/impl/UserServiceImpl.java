package net.skytelecom.services.impl;

import java.util.List;
import net.skytelecom.dao.IGenericDao;
import net.skytelecom.dao.IUserDao;
import net.skytelecom.entity.User;
import net.skytelecom.services.IUserService;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 02.07.2010
 */
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements IUserService {

    private IUserDao userDao;

    public UserServiceImpl(IGenericDao<User, Long> genericDao) {
        super(genericDao);
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
