package net.skytelecom.services.impl;

import java.util.List;
import net.skytelecom.dao.ICustomerDao;
import net.skytelecom.dao.IGenericDao;
import net.skytelecom.entity.User;
import net.skytelecom.services.ICustomerService;
import net.skytelecom.entity.Customer;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 28.06.2010
 */
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Long> implements ICustomerService {

    private ICustomerDao customerDao;

    public CustomerServiceImpl(IGenericDao<Customer, Long> genericDao) {
        super(genericDao);
    }

    public ICustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(ICustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public List<Customer> findByUser(User user) {
        return customerDao.findByUser(user);
    }

    @Override
    public List<Customer> findByUserAndWithPriceList(User user) {
        return customerDao.findByUserAndWithPriceList(user);
    }
}
