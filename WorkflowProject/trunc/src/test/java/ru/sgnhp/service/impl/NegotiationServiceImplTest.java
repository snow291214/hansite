package ru.sgnhp.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.NegotiationBean;
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
public class NegotiationServiceImplTest extends AbstractTransactionalDataSourceSpringContextTests  {
    private INegotiationService negotiationService;
    private INegotiationTypeService negotiationTypeService;
    private IUserManagerService userManagerService;
    private INegotiationFileService negotiationFileService;
    private IConclusionService conclusionService;
    private IConclusionTypeService conclusionTypeService;

//    @Test
//    public void testCreateNewNegotiationProcess() {
//        String[] list = {"2","3","4"};
//        NegotiationBean negotiationBean = getNegotiationService().createNewNegotiationProcess(DateUtils.nowDate(), "1", list, "1", null);
//        
//        
//        assertNotNull(negotiationBean);
//        //assertNotNull(negotiationBean.getNegotiationFileBeanCollection());
//    }
    
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
    
    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    /**
     * @return the negotiationService
     */
    public INegotiationService getNegotiationService() {
        return negotiationService;
    }

    /**
     * @param negotiationService the negotiationService to set
     */
    public void setNegotiationService(INegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }

    /**
     * @return the negotiationService
     */
}
