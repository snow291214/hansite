package net.skytelecom.dao;

import java.util.List;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.Price;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 03.07.2010
 */
public interface IPriceDao extends IGenericDao<Price, Long> {

    List<Price> findByCustomer(Customer customer);

    List<String> findDistinctDestinations(Long customerUid);

    List<String> findAreaCodesByCustomerUid(Long customerUid);

    List<Price> findByDestinationName(String destination, Long customerUid);
}
