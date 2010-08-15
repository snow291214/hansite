package net.skytelecom.dao;

import java.util.List;
import java.util.Map;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.PriceType;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 09.08.2010
 */
public interface IPriceTypeDao extends IGenericDao<PriceType, Long> {

    List<Map<Long, String>>  findPriceTypesByCustomer(Customer customer);
}
