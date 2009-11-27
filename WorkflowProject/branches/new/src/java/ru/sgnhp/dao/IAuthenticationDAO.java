package ru.sgnhp.dao;

import java.util.Map;
import ru.sgnhp.domain.UserLogin;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IAuthenticationDAO {
    Map authenticateUser(UserLogin login);
}
