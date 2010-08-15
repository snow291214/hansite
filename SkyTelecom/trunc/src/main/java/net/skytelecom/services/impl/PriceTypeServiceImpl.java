package net.skytelecom.services.impl;

import java.util.List;
import java.util.Map;
import net.skytelecom.dao.IGenericDao;
import net.skytelecom.dao.IPriceTypeDao;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.PriceType;
import net.skytelecom.services.IPriceTypeService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 09.08.2010
 */

public class PriceTypeServiceImpl extends GenericServiceImpl<PriceType, Long> implements IPriceTypeService {

    private IPriceTypeDao priceTypeDao;

    public PriceTypeServiceImpl(IGenericDao<PriceType, Long> genericDao) {
        super(genericDao);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public List<Map<Long, String>> findPriceTypesByCustomer(Customer customer) {
        return getPriceTypeDao().findPriceTypesByCustomer(customer);
    }

    public IPriceTypeDao getPriceTypeDao() {
        return priceTypeDao;
    }

    public void setPriceTypeDao(IPriceTypeDao priceTypeDao) {
        this.priceTypeDao = priceTypeDao;
    }
}
