package ru.sgnhp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.DateUtils;
import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.dao.INegotiationDao;
import ru.sgnhp.domain.ConclusionBean;
import ru.sgnhp.domain.NegotiationBean;
import ru.sgnhp.domain.NegotiationFileBean;
import ru.sgnhp.domain.NegotiationTypeBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IConclusionService;
import ru.sgnhp.service.IConclusionTypeService;
import ru.sgnhp.service.INegotiationFileService;
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
    private INegotiationFileService negotiationFileService;
    private IConclusionService conclusionService;
    private IConclusionTypeService conclusionTypeService;

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

        WorkflowUserBean curator = getUserManagerService().getUserByLogin(curatorUid);
        NegotiationTypeBean negotiationTypeBean = this.getNegotiationTypeService().get(Long.getLong(negotiationType));

        //Новый процесс
        NegotiationBean negotiationBean = new NegotiationBean();
        negotiationBean.setDueDate(dueDate);
        negotiationBean.setStartDate(DateUtils.nowDate());
        negotiationBean.setNegotiationTypeBean(negotiationTypeBean);
        negotiationBean.setWorkflowUserBean(curator);
        //Добавление файлов к процессу
        negotiationBean.setNegotiationFileBeanCollection(negotiationFileBeans);
        negotiationBean = negotiationDao.save(negotiationBean);

        /*
         * После создании согласования, создается список
         * conclusions (заключения).
         * Все conclusions по умолчанию создаются "В работе, uid=3"
         * 
         */
        for (String negotiatorUid : negotiatorUids) {
            WorkflowUserBean workflowUserBean = this.getUserManagerService().get(Long.parseLong(negotiatorUid));
            ConclusionBean conclusionBean = new ConclusionBean();
            conclusionBean.setConclusionTypeBean(conclusionTypeService.get(3L));
            conclusionBean.setNegotiationBean(negotiationBean);
            conclusionBean.setWorkflowUserBean(workflowUserBean);
            conclusionService.save(conclusionBean);
        }
        return negotiationBean;
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

    /**
     * @return the negotiationFileService
     */
    public INegotiationFileService getNegotiationFileService() {
        return negotiationFileService;
    }

    /**
     * @param negotiationFileService the negotiationFileService to set
     */
    public void setNegotiationFileService(INegotiationFileService negotiationFileService) {
        this.negotiationFileService = negotiationFileService;
    }

    /**
     * @return the conclusionService
     */
    public IConclusionService getConclusionService() {
        return conclusionService;
    }

    /**
     * @param conclusionService the conclusionService to set
     */
    public void setConclusionService(IConclusionService conclusionService) {
        this.conclusionService = conclusionService;
    }

    /**
     * @return the conclusionTypeService
     */
    public IConclusionTypeService getConclusionTypeService() {
        return conclusionTypeService;
    }

    /**
     * @param conclusionTypeService the conclusionTypeService to set
     */
    public void setConclusionTypeService(IConclusionTypeService conclusionTypeService) {
        this.conclusionTypeService = conclusionTypeService;
    }
}
