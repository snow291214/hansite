package net.skytelecom.dao.test;

import net.skytelecom.dao.IPriceDao;
import net.skytelecom.utils.DateUtils;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 26.09.2010
 */
public class PriceDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    private IPriceDao priceDao;

    public PriceDaoTest() {
    }

    @Test
    public void testFindByExpiredDate() {
        assertNotNull(priceDao.findByExpiredDate(DateUtils.nowDate()));
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }
    /**
     * @return the priceDao
     */
    public IPriceDao getPriceDao() {
        return priceDao;
    }

    /**
     * @param priceDao the priceDao to set
     */
    public void setPriceDao(IPriceDao priceDao) {
        this.priceDao = priceDao;
    }
}
