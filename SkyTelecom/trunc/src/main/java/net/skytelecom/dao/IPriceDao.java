package net.skytelecom.dao;

import java.util.Date;
import java.util.List;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 03.07.2010
 */
public interface IPriceDao extends IGenericDao<Price, Long> {

    List<Price> findByCustomer(Customer customer);

    List<String> findDistinctDestinations(Long customersPricesUid);

    List<String> findAreaCodesByCustomerUid(Long customersPricesUid);

    List<Price> findByDestinationName(String destination, Long customersPricesUid);

    List<Price> findByExpiredDate(Date date);

    int deleteByCustomersPrices(CustomersPrices customersPrices);

    int deleteByDestinationName(String destinationName, Long customersPricesUid);

    void batchSave(Price price, Boolean flush);

    void batchSaveEx(List<Price> prices);
}
