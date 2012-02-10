package ru.sgnhp.dao.impl;

import java.util.Collections;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import ru.sgnhp.dao.IDepartmentDao;
import ru.sgnhp.domain.Department;

/**
 *
 * Skype: khudyakov.alexey Email: khudyakov.alexey@gmail.com
 *
 */
public class DepartmentDaoImpl extends GenericDaoHibernate<Department, Long> implements IDepartmentDao {

    public DepartmentDaoImpl() {
        super(Department.class);
    }

    @Override
    public List<Department> getAll() {

//        Criteria criteria = getSession().createCriteria(CustomersPrices.class).
//                setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
//                .add(Restrictions.eq("uid", uid))
//                .createCriteria("prices", Criteria.LEFT_JOIN)
//                .add(Restrictions.ne("priceIndicator", "current"));
//        if (criteria.list() == null || criteria.list().isEmpty()) {
//            CustomersPrices customersPrices = this.get(uid);
//            customersPrices.setPrices(null);
//            return customersPrices;
//        }
//        return (CustomersPrices) criteria.list().get(0);
//    }
        Criteria criteria = getSession().createCriteria(Department.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createCriteria("workflowUserBeanCollection", Criteria.LEFT_JOIN).add(Restrictions.ne("enabled", false));
        //.addOrder(Order.asc("departmentName"));

        if (criteria.list() == null || criteria.list().isEmpty()) {
            return null;
        }
        List<Department> departments = criteria.list();
        Collections.sort(departments);
        return departments;
    }
}
