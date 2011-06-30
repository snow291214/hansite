package ru.sgnhp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.DateUtils;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.INegotiationDao;
import ru.sgnhp.domain.NegotiationBean;
import ru.sgnhp.domain.NegotiationFileBean;
import ru.sgnhp.domain.NegotiationTypeBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.INegotiationService;
import ru.sgnhp.service.INegotiationTypeService;
import ru.sgnhp.service.IUserManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class NegotiationServiceImpl extends GenericServiceImpl<NegotiationBean, Long> implements INegotiationService {

    private INegotiationDao negotiationDao;
    private INegotiationTypeService negotiationTypeService;
    private IUserManagerService userManagerService;

    public NegotiationServiceImpl(IGenericDao<NegotiationBean, Long> genericDao) {
        super(genericDao);
    }

    /**
     * @return the negotiationDao
     */
    public INegotiationDao getNegotiationDao() {
        return negotiationDao;
    }

    /**
     * @param negotiationDao the negotiationDao to set
     */
    public void setNegotiationDao(INegotiationDao negotiationDao) {
        this.negotiationDao = negotiationDao;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public NegotiationBean createNewNegotiationProcess(Date dueDate, String curatorUid,
            List<String> negotiatorUids, String negotiationType,
            Set<NegotiationFileBean> negotiationFileBeans) {
        /*
         * После создании согласования, автоматически создается список
         * conclusions (заключения).
         * Все conclusions по умолчанию создаются "В работе, uid=3"
         * 
         */
        WorkflowUserBean curator = getUserManagerService().getUserByLogin(curatorUid);
        NegotiationTypeBean negotiationTypeBean = this.getNegotiationTypeService().get(Long.getLong(negotiationType));
        
        //Новый процесс
        NegotiationBean negotiationBean = new NegotiationBean();
        negotiationBean.setDueDate(dueDate);
        negotiationBean.setStartDate(DateUtils.nowDate());
        negotiationBean.setNegotiationTypeBean(negotiationTypeBean);
        negotiationBean.setWorkflowUserBean(curator);
        negotiationBean = negotiationDao.save(negotiationBean);
        
        //Добавление файлов к процессу
        
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the userManagerService
     */
    public IUserManagerService getUserManagerService() {
        return userManagerService;
    }

    /**
     * @param userManagerService the userManagerService to set
     */
    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    /**
     * @return the negotiationTypeService
     */
    public INegotiationTypeService getNegotiationTypeService() {
        return negotiationTypeService;
    }

    /**
     * @param negotiationTypeService the negotiationTypeService to set
     */
    public void setNegotiationTypeService(INegotiationTypeService negotiationTypeService) {
        this.negotiationTypeService = negotiationTypeService;
    }
}
