package ru.sgnhp.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ***
 *
 * @author Alexey Khudyakov @Skype: khudyakov.alexey
 *
 *****
 */
public class DocumentDto implements Serializable {

    private static final long serialVersionUID = 43L;
    private Long uid;
    private int incomingNumber;
    private String documentPrefix;
    private Date documentDate;
    private String description;
    private Long documentTypeUid;
    private Long workflowUserUid;
    private long contactUserUid;
    private long controlUserUid;
    private List<DocumentFileDto> documents;
    private String externalCompany;
    private String[] userUids;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDocumentTypeUid() {
        return documentTypeUid;
    }

    public void setDocumentTypeUid(Long documentTypeUid) {
        this.documentTypeUid = documentTypeUid;
    }

    public Long getWorkflowUserUid() {
        return workflowUserUid;
    }

    public void setWorkflowUserUid(Long workflowUserUid) {
        this.workflowUserUid = workflowUserUid;
    }

    public int getIncomingNumber() {
        return incomingNumber;
    }

    public void setIncomingNumber(int incomingNumber) {
        this.incomingNumber = incomingNumber;
    }

    /**
     * @return the documentPrefix
     */
    public String getDocumentPrefix() {
        return documentPrefix;
    }

    /**
     * @param documentPrefix the documentPrefix to set
     */
    public void setDocumentPrefix(String documentPrefix) {
        this.documentPrefix = documentPrefix;
    }

    /**
     * @return the contactUserUid
     */
    public Long getContactUserUid() {
        return contactUserUid;
    }

    /**
     * @param contactUserUid the contactUserUid to set
     */
    public void setContactUserUid(Long contactUserUid) {
        this.contactUserUid = contactUserUid;
    }

    /**
     * @return the controlUserUid
     */
    public Long getControlUserUid() {
        return controlUserUid;
    }

    /**
     * @param controlUserUid the controlUserUid to set
     */
    public void setControlUserUid(Long controlUserUid) {
        this.controlUserUid = controlUserUid;
    }

    /**
     * @return the documents
     */
    public List<DocumentFileDto> getDocuments() {
        return documents;
    }

    /**
     * @param documents the documents to set
     */
    public void setDocuments(List<DocumentFileDto> documents) {
        this.documents = documents;
    }

    /**
     * @return the externalCompany
     */
    public String getExternalCompany() {
        return externalCompany;
    }

    /**
     * @param externalCompany the externalCompany to set
     */
    public void setExternalCompany(String externalCompany) {
        this.externalCompany = externalCompany;
    }

    /**
     * @return the userUids
     */
    public String[] getUserUids() {
        return userUids;
    }

    /**
     * @param userUids the userUids to set
     */
    public void setUserUids(String[] userUids) {
        this.userUids = userUids;
    }
}
