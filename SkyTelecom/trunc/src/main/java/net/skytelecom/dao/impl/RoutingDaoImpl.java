package net.skytelecom.dao.impl;

import net.skytelecom.dao.IRoutingDao;
import net.skytelecom.entity.Routing;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 18.07.2010
 */
public class RoutingDaoImpl extends GenericDaoHibernate<Routing, Long> implements IRoutingDao {

    public RoutingDaoImpl() {
        super(Routing.class);
    }
}
