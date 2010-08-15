package net.skytelecom.dao;

import java.util.List;
import net.skytelecom.entity.User;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 02.07.2010
 */
public interface IUserDao extends IGenericDao<User, Long> {

    List<User> findByUsername(String username);
}
