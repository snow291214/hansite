package net.skytelecom.services.impl;

import java.util.List;
import net.skytelecom.dao.ICustomersPricesDao;
import net.skytelecom.dao.IGenericDao;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.services.ICustomersPricesService;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 15.08.2010
 */

public class CustomersPricesServiceImpl extends GenericServiceImpl<CustomersPrices, Long> implements ICustomersPricesService {

    private ICustomersPricesDao customersPricesDao;

    public CustomersPricesServiceImpl(IGenericDao<CustomersPrices, Long> genericDao) {
        super(genericDao);
    }

    public ICustomersPricesDao getCustomersPricesDao() {
        return customersPricesDao;
    }

    public void setCustomersPricesDao(ICustomersPricesDao customersPricesDao) {
        this.customersPricesDao = customersPricesDao;
    }

    @Override
    public CustomersPrices findByChangedIndicators(Long uid) {
        return this.getCustomersPricesDao().findByChangedIndicators(uid);
    }
}
