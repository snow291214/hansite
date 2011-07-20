package ru.sgnhp.dto;

import java.util.Collection;
import java.util.Date;
import ru.sgnhp.domain.ConclusionBean;
import ru.sgnhp.domain.NegotiationFileBean;
import ru.sgnhp.domain.WorkflowUserBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class NegotiationDto {
    private Date startDate;
    private Date dueDate;
    private Date finishDate;
    private WorkflowUserBean workflowUserBean;
    private String[] negotiatorUids;
    private Long uid;
    private Collection<ConclusionBean> conclusionBeanCollection;
    private Collection<NegotiationFileBean> negotiationFileBeanCollection;
    private String negotiationTypeUid;
    private String description;

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the finishDate
     */
    public Date getFinishDate() {
        return finishDate;
    }

    /**
     * @param finishDate the finishDate to set
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * @return the workflowUserBean
     */
    public WorkflowUserBean getWorkflowUserBean() {
        return workflowUserBean;
    }

    /**
     * @param workflowUserBean the workflowUserBean to set
     */
    public void setWorkflowUserBean(WorkflowUserBean workflowUserBean) {
        this.workflowUserBean = workflowUserBean;
    }

    /**
     * @return the uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * @return the conclusionBeanCollection
     */
    public Collection<ConclusionBean> getConclusionBeanCollection() {
        return conclusionBeanCollection;
    }

    /**
     * @param conclusionBeanCollection the conclusionBeanCollection to set
     */
    public void setConclusionBeanCollection(Collection<ConclusionBean> conclusionBeanCollection) {
        this.conclusionBeanCollection = conclusionBeanCollection;
    }

    /**
     * @return the negotiationFileBeanCollection
     */
    public Collection<NegotiationFileBean> getNegotiationFileBeanCollection() {
        return negotiationFileBeanCollection;
    }

    /**
     * @param negotiationFileBeanCollection the negotiationFileBeanCollection to set
     */
    public void setNegotiationFileBeanCollection(Collection<NegotiationFileBean> negotiationFileBeanCollection) {
        this.negotiationFileBeanCollection = negotiationFileBeanCollection;
    }

    /**
     * @return the negotiationTypeUid
     */
    public String getNegotiationTypeUid() {
        return negotiationTypeUid;
    }

    /**
     * @param negotiationTypeUid the negotiationTypeUid to set
     */
    public void setNegotiationTypeUid(String negotiationTypeUid) {
        this.negotiationTypeUid = negotiationTypeUid;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the negotiatorUids
     */
    public String[] getNegotiatorUids() {
        return negotiatorUids;
    }

    /**
     * @param negotiatorUids the negotiatorUids to set
     */
    public void setNegotiatorUids(String[] negotiatorUids) {
        this.negotiatorUids = negotiatorUids;
    }
}
