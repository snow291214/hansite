package net.skytelecom.dao;

import java.util.List;
import net.skytelecom.entity.CustomersPrices;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 15.08.2010
 */
public interface ICustomersPricesDao extends IGenericDao<CustomersPrices, Long> {

    CustomersPrices findByChangedIndicators(Long uid);
}
