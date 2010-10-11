package net.skytelecom.dao.test;

import net.skytelecom.dao.ICustomersPricesDao;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 11.10.2010
 */
public class CustomersPricesDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    private ICustomersPricesDao customersPricesDao;

    public CustomersPricesDaoTest() {
    }

    @Test
    public void testFindByChangedIndicators() {
        assertNotNull(customersPricesDao.findByChangedIndicators(10L));
        assertNull(customersPricesDao.findByChangedIndicators(10L).getPrices());
        logger.warn(customersPricesDao.findByChangedIndicators(6L).getPrices());
        //assertEquals(customersPricesDao.findByChangedIndicators(6L).getPrices().size(), 1);
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    public ICustomersPricesDao getCustomersPricesDao() {
        return customersPricesDao;
    }

    public void setCustomersPricesDao(ICustomersPricesDao customersPricesDao) {
        this.customersPricesDao = customersPricesDao;
    }
}
