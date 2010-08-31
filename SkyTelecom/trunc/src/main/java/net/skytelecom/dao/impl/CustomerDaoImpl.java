package net.skytelecom.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.skytelecom.dao.ICustomerDao;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.User;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 28.06.2010
 */
public class CustomerDaoImpl extends GenericDaoHibernate<Customer, Long> implements ICustomerDao {

    public CustomerDaoImpl() {
        super(Customer.class);
    }

    @Override
    public List<Customer> findByUser(User user) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("user", user);
        List<Customer> list = this.findByNamedQuery("Customer.findByUser", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<Customer> findByUserAndWithPriceList(User user) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("user", user);
        List<Customer> list = this.findByNamedQuery("Customer.findByUserAndWithPriceList", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }
}
