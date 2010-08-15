package net.skytelecom.dao.impl;

import net.skytelecom.dao.ICustomersPricesDao;
import net.skytelecom.entity.CustomersPrices;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 15.08.2010
 */
public class CustomersPricesDaoImpl extends GenericDaoHibernate<CustomersPrices, Long> implements ICustomersPricesDao {

    public CustomersPricesDaoImpl() {
        super(CustomersPrices.class);
    }
}
