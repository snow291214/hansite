package ru.sgnhp.dto;

import java.io.Serializable;
import java.util.Date;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class OutgoingMailDto implements Serializable {

    private static final long serialVersionUID = 8L;
    private Long uid;
    private Long outgoingNumber;
    private String documentumNumber;
    private String primaveraUid;
    private String description;
    private Date outgoingDate;
    private String receiverCompany;
    private String receiverName;
    private Long responsibleUid;
    private Date dueDate;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getOutgoingNumber() {
        return outgoingNumber;
    }

    public void setOutgoingNumber(Long outgoingNumber) {
        this.outgoingNumber = outgoingNumber;
    }

    public String getDocumentumNumber() {
        return documentumNumber;
    }

    public void setDocumentumNumber(String documentumNumber) {
        this.documentumNumber = documentumNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getOutgoingDate() {
        return outgoingDate;
    }

    public void setOutgoingDate(Date outgoingDate) {
        this.outgoingDate = outgoingDate;
    }

    public String getReceiverCompany() {
        return receiverCompany;
    }

    public void setReceiverCompany(String receiverCompany) {
        this.receiverCompany = receiverCompany;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Long getResponsibleUid() {
        return responsibleUid;
    }

    public void setResponsibleUid(Long responsibleUid) {
        this.responsibleUid = responsibleUid;
    }

    public String getPrimaveraUid() {
        return primaveraUid;
    }

    public void setPrimaveraUid(String primaveraUid) {
        this.primaveraUid = primaveraUid;
    }
}
