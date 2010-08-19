package net.skytelecom.services.impl;

import java.util.List;
import net.skytelecom.dao.IGenericDao;
import net.skytelecom.dao.IPriceDao;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;
import net.skytelecom.services.IPriceService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 04.07.2010
 */
public class PriceServiceImpl extends GenericServiceImpl<Price, Long> implements IPriceService {

    private IPriceDao priceDao;

    public PriceServiceImpl(IGenericDao<Price, Long> genericDao) {
        super(genericDao);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public List<Price> findByCustomer(Customer customer) {
        return priceDao.findByCustomer(customer);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public void importCSVDatasheet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public IPriceDao getPriceDao() {
        return priceDao;
    }

    public void setPriceDao(IPriceDao priceDao) {
        this.priceDao = priceDao;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public List<String> findDistinctDestinations(Long customerUid) {
        return priceDao.findDistinctDestinations(customerUid);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public List<String> findAreaCodesByCustomerUid(Long customerUid) {
        return priceDao.findAreaCodesByCustomerUid(customerUid);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public List<Price> findByDestinationName(String destination, Long customerUid) {
        return priceDao.findByDestinationName(destination, customerUid);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public Price getOldDestinationRateByDestinationName(String destination, Long customerUid) {
        return this.findByDestinationName(destination, customerUid).get(0);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public int deleteByCustomersPrices(CustomersPrices customersPrices) {
        return priceDao.deleteByCustomersPrices(customersPrices);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public Price batchSave(Price price, Boolean flush) {
        return priceDao.batchSave(price, flush);
    }
}
