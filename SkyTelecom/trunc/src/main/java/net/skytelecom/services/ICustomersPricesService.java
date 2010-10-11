package net.skytelecom.services;

import java.util.List;
import net.skytelecom.entity.CustomersPrices;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 15.08.2010
 */
public interface ICustomersPricesService extends IGenericService<CustomersPrices, Long> {

    CustomersPrices findByChangedIndicators(Long uid);
}
