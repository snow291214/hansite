package net.skytelecom.dto;

import java.io.Serializable;
import java.util.Date;
import net.skytelecom.entity.Customer;
import net.skytelecom.entity.User;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 28.06.2010
 */
public class CustomerDto implements Serializable {
    private static final long serialVersionUID = 5721319090250647810L;
    private Long uid;
    private String customerName;
    private String customerId;
    private String currency;
    private String contactPerson;
    private String email;
    private String hiddenEmail;
    private String emailSubject;
    private String blockedDestinations;
    private Date latestReport;
    private String sender;
    private User user;

    public CustomerDto() {
    }

    public CustomerDto(Customer customer) {
        this.uid = customer.getUid();
        this.customerName = customer.getCustomerName();
        this.customerId = customer.getCustomerId();
        this.currency = customer.getCurrency();
        this.contactPerson = customer.getContactPerson();
        this.email = customer.getEmail();
        this.hiddenEmail = customer.getHiddenEmail();
        this.emailSubject = customer.getEmailSubject();
        this.blockedDestinations = customer.getBlockedDestinations();
        this.latestReport = customer.getLatestReport();
        this.sender = customer.getSender();
        this.user = customer.getUser();
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHiddenEmail() {
        return hiddenEmail;
    }

    public void setHiddenEmail(String hiddenEmail) {
        this.hiddenEmail = hiddenEmail;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getBlockedDestinations() {
        return blockedDestinations;
    }

    public void setBlockedDestinations(String blockedDestinations) {
        this.blockedDestinations = blockedDestinations;
    }

    public Date getLatestReport() {
        return latestReport;
    }

    public void setLatestReport(Date latestReport) {
        this.latestReport = latestReport;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
