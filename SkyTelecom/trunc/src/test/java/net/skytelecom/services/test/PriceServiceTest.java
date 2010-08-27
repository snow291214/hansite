package net.skytelecom.services.test;

import net.skytelecom.services.ICustomerService;
import net.skytelecom.services.IPriceService;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 04.07.2010
 */
public class PriceServiceTest extends AbstractTransactionalDataSourceSpringContextTests {

    private IPriceService priceService;
    private ICustomerService customerService;

    public PriceServiceTest() {
    }

//    @Test
//    public void testFindByCustomer() {
//        Customer customer = customerService.get(1L);
//        assertNotNull(priceService.findByCustomer(customer));
////        logger.warn(customer.getPrices().size());
//    }

    @Test
    public void testFindDistinctDestinations() {
        assertNotNull(priceService.findDistinctDestinations(1L));
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    public IPriceService getPriceService() {
        return priceService;
    }

    public void setPriceService(IPriceService priceService) {
        this.priceService = priceService;
    }

    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }
}
