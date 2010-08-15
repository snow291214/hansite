package net.skytelecom.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.skytelecom.dao.IPriceDao;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.Price;
import org.hibernate.Query;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 04.07.2010
 */
public class PriceDaoImpl extends GenericDaoHibernate<Price, Long> implements IPriceDao {

    public PriceDaoImpl() {
        super(Price.class);
    }

    @Override
    public List<Price> findByCustomer(Customer customer) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("customer", customer);
        List<Price> list = this.findByNamedQuery("Price.findByCustomer", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<String> findDistinctDestinations(Long customerUid) {
        Query query = getSession().createQuery("SELECT distinct p.destination "
                + "FROM Price p where p.customer.uid = :customerUid order by p.destination");
        query.setParameter("customerUid", customerUid);
        List<String> result = query.list();
        return result;
    }

    @Override
    public List<String> findAreaCodesByCustomerUid(Long customerUid) {
        Query query = getSession().createQuery("SELECT p.phoneCode "
                + "FROM Price p where p.customer.uid = :customerUid");
        query.setParameter("customerUid", customerUid);
        List<String> result = query.list();
        return result;
    }
    
    @Override
    public List<Price> findByDestinationName(String destination, Long customerUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("destination", destination);
        value.put("customerUid", customerUid);
        List<Price> list = this.findByNamedQuery("Price.findByDestination", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }
}
