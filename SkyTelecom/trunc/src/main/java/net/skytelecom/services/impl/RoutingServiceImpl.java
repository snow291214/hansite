package net.skytelecom.services.impl;

import net.skytelecom.dao.IGenericDao;
import net.skytelecom.dao.IRoutingDao;
import net.skytelecom.entity.Routing;
import net.skytelecom.services.IRoutingService;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 18.07.2010
 */
public class RoutingServiceImpl extends GenericServiceImpl<Routing, Long> implements IRoutingService {

    private IRoutingDao routingDao;

    public RoutingServiceImpl(IGenericDao<Routing, Long> genericDao) {
        super(genericDao);
    }

    public IRoutingDao getRoutingDao() {
        return routingDao;
    }

    public void setRoutingDao(IRoutingDao routingDao) {
        this.routingDao = routingDao;
    }
}
