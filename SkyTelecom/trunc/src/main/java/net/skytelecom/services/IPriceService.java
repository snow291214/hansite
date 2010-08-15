package net.skytelecom.services;

import java.util.List;
import net.skytelecom.entity.Customer;
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

    List<String> findDistinctDestinations(Long customerUid);

    List<Price> findByDestinationName(String destination, Long customerUid);

    List<String> findAreaCodesByCustomerUid(Long customerUid);

    Price getOldDestinationRateByDestinationName(String destination, Long customerUid);
}
