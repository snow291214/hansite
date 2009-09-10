package ru.sgnhp.service.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.sgnhp.dao.IUserDao;
import ru.sgnhp.dao.IWorkflowDao;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IUserManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class UserManagerServiceImpl implements IUserManagerService{

    private IUserDao userDao;
    private IWorkflowManagerService workflowManagerService;
    protected final Log logger = LogFactory.getLog(getClass());

    public void registerNewUser(WorkflowUserBean siteUser) {
        userDao.save(siteUser);
    }

    public void updateUser(WorkflowUserBean siteUser) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteUser(Long userUid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public WorkflowUserBean getUserByLogin(String login) {
        WorkflowUserBean user = userDao.getByLogin(login);
        user.setWorkflows(workflowManagerService.getRecievedWorkflowsByUserUid(user.getUid()));
        return user;
    }

    public WorkflowUserBean getUserByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public WorkflowUserBean getUserByUid(Long userUid) {
        WorkflowUserBean user = userDao.getByUid(userUid);
        return user;
    }

    public List<WorkflowUserBean> getAllUsers() {
        return userDao.getAllUsers();
    }

    public List<WorkflowUserBean> getAllNormalizedUsers() {
        return userDao.getNormalisedUsers();
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
