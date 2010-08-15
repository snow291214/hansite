package net.skytelecom.services;

import java.util.List;
import net.skytelecom.entity.User;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 02.07.2010
 */
public interface IUserService extends IGenericService<User, Long> {

    List<User> findByUsername(String username);
}
