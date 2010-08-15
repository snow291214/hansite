package net.skytelecom.dao.impl;

import java.util.List;
import java.util.Map;
import net.skytelecom.dao.IPriceTypeDao;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.PriceType;
import org.hibernate.Query;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 09.08.2010
 */
public class PriceTypeDaoImpl extends GenericDaoHibernate<PriceType, Long> implements IPriceTypeDao {

    public PriceTypeDaoImpl() {
        super(PriceType.class);
    }

    @Override
    public List<Map<Long, String>> findPriceTypesByCustomer(Customer customer) {
        Query query = getSession().createQuery("SELECT distinct new map (p.priceType.name) from "
                + "Price p where p.customer = :customer order by p.priceType");
        query.setParameter("customer", customer);
        return query.list();
    }
}
