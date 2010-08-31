package net.skytelecom.dao;

import java.util.List;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.User;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 28.06.2010
 */
public interface ICustomerDao extends IGenericDao<Customer, Long> {

    List<Customer> findByUser(User user);

    List<Customer> findByUserAndWithPriceList(User user);
}
