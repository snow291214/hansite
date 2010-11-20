package net.skytelecom.services;

import java.util.Date;
import java.util.List;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 04.07.2010
 */
public interface IPriceService extends IGenericService<Price, Long> {

    List<Price> findByCustomer(Customer customer);

    void importCSVDatasheet();

    List<String> findDistinctDestinations(Long customerPricesUid);

    List<Price> findByDestinationName(String destination, Long customersPricesUid);

    List<String> findAreaCodesByCustomerUid(Long customerUid);

    Price getOldDestinationRateByDestinationName(String destination, Long customerUid);

    int deleteByCustomersPrices(CustomersPrices customersPrices);

    void deleteByDestinationsNames(String[] destinations, Long customersPricesUid);

    void batchSave(Price price, Boolean flush);

    void batchSaveEx(List<Price> prices);

    Price fillingPricePropertiesFromCsvLine(String[] a, CustomersPrices customersPrices);

    List<Price> findByExpiredDate(Date date);

    public void changePriceIndicator();
}
