package net.skytelecom.dao.impl;

import net.skytelecom.dao.ICustomersPricesDao;
import net.skytelecom.entity.CustomersPrices;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

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

    @Override
    public CustomersPrices findByChangedIndicators(Long uid) {
        /*
        Criteria c = this.getSession().createCriteria(Cat.class)
        .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
        .add(Restrictions.eq(”color”, “white”))
        .createCriteria(”kittens”, Criteria.LEFT_JOIN)
        .add(Restrictions.eq(”color”, “white”))
        ;
         */
        Criteria criteria = getSession().createCriteria(CustomersPrices.class).
                setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("uid", uid))
                .createCriteria("prices", Criteria.LEFT_JOIN)
                .add(Restrictions.ne("priceIndicator", "current"));
        if (criteria.list() == null || criteria.list().isEmpty()) {
            CustomersPrices customersPrices = this.get(uid);
            customersPrices.setPrices(null);
            return customersPrices;
        }
        return (CustomersPrices) criteria.list().get(0);
    }
}
