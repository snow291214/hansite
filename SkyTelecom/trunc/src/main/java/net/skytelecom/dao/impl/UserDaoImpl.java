package net.skytelecom.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.skytelecom.dao.IUserDao;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.User;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 02.07.2010
 */
public class UserDaoImpl extends GenericDaoHibernate<User, Long> implements IUserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public List<User> findByUsername(String username) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("username", username);
        List<User> list = this.findByNamedQuery("User.findByUsername", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }
}
