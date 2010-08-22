package net.skytelecom.services.impl;

import java.util.Date;
import java.util.List;
import net.skytelecom.dao.IGenericDao;
import net.skytelecom.dao.IPriceDao;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;
import net.skytelecom.services.IPriceService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.DateUtils;

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
    public void batchSave(Price price, Boolean flush) {
        priceDao.batchSave(price, flush);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void batchSaveEx(List<Price> prices) {
        priceDao.batchSaveEx(prices);
    }

    @Override
    public Price fillingPricePropertiesFromCsvLine(String line, CustomersPrices customersPrices) {
        String[] a = line.split(";");
        Price price = new Price();
        price.setCustomersPrices(customersPrices);
        price.setPhoneCode(a[2].trim());
        price.setDestination(a[3]);
        price.setRatePeak(Double.parseDouble(a[5]));
        price.setRateOffpeak(Double.parseDouble(a[5]));
        price.setQos(a[4]);
        Date date = DateUtils.stringToDate(a[6], "dd-MM-yyyy");
        price.setActivationDate(date);
        price.setConnectRateOffpeak(0D);
        price.setConnectRatePeak(0D);
        price.setCurrency(a[7]);
        price.setFreeOffpeak(0D);
        price.setFreePeak(0D);
        price.setInitPeak(1D);
        price.setInitOffpeak(1D);
        price.setLastFieldIgnore(Short.parseShort("0"));
        price.setPriceIndicator("current");
        price.setQuantPeak(1D);
        price.setQuantOffpeak(1D);
        return price;
    }
}
