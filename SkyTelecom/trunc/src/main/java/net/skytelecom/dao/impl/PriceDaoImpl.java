package net.skytelecom.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.skytelecom.dao.IPriceDao;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.CustomersPrices;
import net.skytelecom.entity.Price;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    public List<String> findDistinctDestinations(Long customersPricesUid) {
        Query query = getSession().createQuery("SELECT distinct p.destination "
                + "FROM Price p where p.customersPrices.uid = :customersPricesUid order by p.destination");
        query.setParameter("customersPricesUid", customersPricesUid);
        List<String> result = query.list();
        return result;
    }

    @Override
    public List<String> findAreaCodesByCustomerUid(Long customersPricesUid) {
        Query query = getSession().createQuery("SELECT p.phoneCode "
                + "FROM Price p where p.customersPrices.uid = :customersPricesUid");
        query.setParameter("customersPricesUid", customersPricesUid);
        List<String> result = query.list();
        return result;
    }

    @Override
    public List<Price> findByDestinationName(String destination, Long customersPricesUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("destination", destination);
        value.put("customersPricesUid", customersPricesUid);
        List<Price> list = this.findByNamedQuery("Price.findByDestination", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public int deleteByCustomersPrices(CustomersPrices customersPrices) {
        Query query = getSession().createQuery("DELETE FROM Price p WHERE "
                + "p.customersPrices = :customersPrices");
        query.setParameter("customersPrices", customersPrices);
        return query.executeUpdate();
    }

    @Override
    public void batchSave(Price price, Boolean flush) {
        if (flush) {
            this.getSession().flush();
            this.getSession().clear();
        }
        this.save(price);
    }

    @Override
    public void batchSaveEx(List<Price> prices) {
        Session session1 = getHibernateTemplate().getSessionFactory().openSession();
        Transaction tx = session1.beginTransaction();
        log.debug("Session Flush mode is  " + session1.getFlushMode());
        int count = 0;
        for (Price price : prices) {
            session1.save(price);
            count++;
            if (count % 30 == 0) { //20, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session1.flush();
                session1.clear();
            }

        }
        tx.commit();
        session1.close();
    }

    @Override
    public List<Price> findByExpiredDate(Date date) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("activationDate", date);
        List<Price> list = this.findByNamedQuery("Price.findByExpiredDate", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public int deleteByDestinationName(String destinationName, Long customersPricesUid) {
        Query query = getSession().createQuery("DELETE FROM Price p WHERE "
                + "p.destination = :destinationName and "
                +"p.customersPrices.uid = :customersPricesUid");
        query.setParameter("destinationName", destinationName);
        query.setParameter("customersPricesUid", customersPricesUid);
        return query.executeUpdate();
    }
}
