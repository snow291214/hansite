package ru.sgnhp.services.impl;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.services.IGenericService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
@Transactional(readOnly = true)
public class GenericService<T, PK extends Serializable> implements IGenericService<T, PK> {

    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    /**
     * GenericDao instance, set by constructor of this class
     */
    protected IGenericDao<T, PK> genericDao;

    /**
     * Public constructor for creating a new GenericManagerImpl.
     * @param genericDao the GenericDao to use for persistence
     */
    public GenericService(final IGenericDao<T, PK> genericDao) {
        this.genericDao = genericDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<T> getAll() {
        return genericDao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public T get(PK id) {
        return genericDao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public boolean exists(PK id) {
        return genericDao.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public T save(T object) {
        return genericDao.save(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void remove(PK id) {
        genericDao.remove(id);
    }
}