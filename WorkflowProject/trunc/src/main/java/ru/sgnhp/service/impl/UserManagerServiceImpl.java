package ru.sgnhp.service.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.IUserDao;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IUserManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
@Transactional(readOnly = true)
public class UserManagerServiceImpl extends GenericServiceImpl<WorkflowUserBean, Long> implements IUserManagerService {

    private IUserDao userDao;
    protected final Log logger = LogFactory.getLog(getClass());

    public UserManagerServiceImpl(IGenericDao<WorkflowUserBean, Long> genericDao) {
        super(genericDao);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public List<WorkflowUserBean> getAll() {
        return userDao.getAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public WorkflowUserBean getUserByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public WorkflowUserBean getUserByEmail(String email) {
        return userDao.getByEmail(email);
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
