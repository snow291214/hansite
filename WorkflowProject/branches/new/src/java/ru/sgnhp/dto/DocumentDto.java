package ru.sgnhp.dto;

import java.util.Date;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class DocumentDto {

    private Long uid;
    private int incomingNumber;
    private Date documentDate;
    private String description;
    private Long documentTypeUid;
    private Long workflowUserUid;

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
}
