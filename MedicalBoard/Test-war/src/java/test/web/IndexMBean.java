/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import ru.salavatmed.ejb.HazardSessionBean;
import ru.salavatmed.ejb.UserSessionBean;
import ru.salavatmed.entity.Hazard;
import ru.salavatmed.entity.User;
import test.web.dto.HazardDto;
import test.web.dto.PatientDto;

/**
 *
 * @author 77han
 */
@Named(value = "indexMBean")
@SessionScoped
public class IndexMBean implements Serializable {
    @EJB
    private HazardSessionBean hazardSessionBean;
    @EJB
    private UserSessionBean userSessionBean;
    private User userDetails;
    private PatientDto patientDto = new PatientDto();
    private String username = "";
    private String appendix;
    private String paragraph;
    private String paragraphDescription;
    //private List<HazardDto> hazards = new ArrayList<HazardDto>();
    private List<SelectItem> hazardList;
    private int counter;

    /** Creates a new instance of IndexMBean */
    public IndexMBean() {
    }

    @PostConstruct
    public void setUserDetailsByPrincipal() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();
        String string = externalContext.getUserPrincipal().getName();
        StringTokenizer tokenizer = new StringTokenizer(string, "=,");
        while (tokenizer.hasMoreTokens()) {
            String key = tokenizer.nextToken().trim();
            String value = tokenizer.nextToken().trim().toUpperCase();

            if ("CN".equals(key)) {
                username = value;
                break;
            }
        }
        this.userDetails = userSessionBean.getUserByUsername(username).get(0);
        this.patientDto.setFormOfProperty(this.userDetails.getAuthority().getFormOfProperty());
        this.patientDto.setOkved(this.userDetails.getAuthority().getOkved());
        this.patientDto.setHrManager(this.userDetails.getFullName());
        this.patientDto.setHrEmail(this.userDetails.getEmail());
        this.patientDto.setCompanyName(this.userDetails.getAuthority().getFullName());
        this.patientDto.setMedialogCode(this.userDetails.getAuthority().getMedialogCode());
        this.patientDto.setHazards(new ArrayList<HazardDto>());
        this.counter = 0;
    }
    
    public void setHazardsByAppendix() {
//        Logger logger = Logger.getAnonymousLogger();
//        logger.warning(this.getAppendix());
        hazardList = new ArrayList<SelectItem>();
        for(Hazard hazard : this.hazardSessionBean.getHazardByAppendix(this.appendix)){
            getHazardList().add(new SelectItem(hazard.getParagraph(), hazard.getParagraph()+" - "+hazard.getName())); 
        }
    }

    public void setHazard() {
        HazardDto hazardDto = new HazardDto();
        hazardDto.setUid(counter++);
        hazardDto.setAppendix(this.appendix);
        hazardDto.setParagraph(this.paragraph);
        hazardDto.setParagraphDescription(hazardSessionBean.getHazardByAppendixAndParagraph(this.appendix, this.paragraph).getName());
        this.patientDto.getHazards().add(hazardDto);
    }
    
    public void deleteTableRow(HazardDto hazardDto){
        Logger logger = Logger.getAnonymousLogger();
        logger.warning(hazardDto.toString());
        patientDto.getHazards().remove(hazardDto);
    }
    
    /**
     * @return the appendix
     */
    public String getAppendix() {
        return appendix;
    }

    /**
     * @param appendix the appendix to set
     */
    public void setAppendix(String appendix) {
        this.appendix = appendix;
    }

    /**
     * @return the patientDto
     */
    public PatientDto getPatientDto() {
        return patientDto;
    }

    /**
     * @param patientDto the patientDto to set
     */
    public void setPatientDto(PatientDto patientDto) {
        this.patientDto = patientDto;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the paragraph
     */
    public String getParagraph() {
        return paragraph;
    }

    /**
     * @param paragraph the paragraph to set
     */
    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    /**
     * @return the paragraphDescription
     */
    public String getParagraphDescription() {
        return paragraphDescription;
    }

    /**
     * @param paragraphDescription the paragraphDescription to set
     */
    public void setParagraphDescription(String paragraphDescription) {
        this.paragraphDescription = paragraphDescription;
    }

//    /**
//     * @return the hazards
//     */
//    public List<HazardDto> getHazards() {
//        return hazards;
//    }
//
//    /**
//     * @param hazards the hazards to set
//     */
//    public void setHazards(List<HazardDto> hazards) {
//        this.hazards = hazards;
//    }

    /**
     * @return the hazardList
     */
    public List<SelectItem> getHazardList() {
        return hazardList;
    }

    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
