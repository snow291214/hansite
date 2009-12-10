package ru.sgnhp.dao;

import java.util.Map;
import ru.sgnhp.dto.UserLogin;

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
