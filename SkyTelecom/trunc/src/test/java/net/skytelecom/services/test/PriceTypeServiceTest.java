package net.skytelecom.services.test;

import java.util.List;
import java.util.Map;
import net.skytelecom.entity.Customer;
import net.skytelecom.services.ICustomerService;
import net.skytelecom.services.IPriceTypeService;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 09.08.2010
 */
public class PriceTypeServiceTest extends AbstractTransactionalDataSourceSpringContextTests {

    public PriceTypeServiceTest() {
    }
    private IPriceTypeService priceTypeService;
    private ICustomerService customerService;

    @Test
    public void testFindByCustomer() {
        Customer customer = customerService.get(1L);
        List<Map<Long, String>> map = priceTypeService.findPriceTypesByCustomer(customer);
        assertNotNull(map);
        logger.error(map.get(0));
    }

    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * @return the priceTypeService
     */
    public IPriceTypeService getPriceTypeService() {
        return priceTypeService;
    }

    /**
     * @param priceTypeService the priceTypeService to set
     */
    public void setPriceTypeService(IPriceTypeService priceTypeService) {
        this.priceTypeService = priceTypeService;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }
}
