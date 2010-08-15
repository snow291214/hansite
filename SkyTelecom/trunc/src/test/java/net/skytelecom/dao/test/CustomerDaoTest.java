package net.skytelecom.dao.test;

import java.util.List;
import net.skytelecom.dao.ICustomerDao;
import net.skytelecom.dao.IUserDao;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.User;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 02.07.2010
 */
public class CustomerDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    private ICustomerDao customerDao;
    private IUserDao userDao;

    public CustomerDaoTest() {
    }

    @Test
    public void testFindByUser() {
        User user = getUserDao().get(1L);
        List<Customer> customers = customerDao.findByUser(user);
        assertNotNull(customers);
    }

    @Test
    public void testCustomerPrices() {
        User user = getUserDao().get(1L);
        List<Customer> customers = customerDao.findByUser(user);
        assertNotNull(customers);
        Customer customer = customers.get(0);
        assertNotNull(customer.getCustomersPrices());
        assertNotNull(customer.getCustomersPrices());
        //logger.warn(customer.getCustomersPrices().size());
        for(CustomersPrices customersPrices : customer.getCustomersPrices()){
            System.out.println(customersPrices.getPriceType().getName());
            System.out.println(customersPrices.getPrices().size());
        }
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    public ICustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(ICustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
