package ru.sgnhp.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
@Entity
@Table(name = "users")
public class Users implements Serializable{
    @Id
    @Column(name = "Uid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;
    @Column(name = "groupUid")
    private Long groupUid;
    @Column(name = "Login")
    private String login;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "MiddleName")
    private String middleName;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "Email")
    private String email;
    @Column(name = "SessionUid")
    private String sessionUid;
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
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the firstNameName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstNameName the firstNameName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the groupUid
     */
    public Long getGroupUid() {
        return groupUid;
    }

    /**
     * @param groupUid the groupUid to set
     */
    public void setGroupUid(Long groupUid) {
        this.groupUid = groupUid;
    }

    public String getSessionUid() {
        return sessionUid;
    }

    public void setSessionUid(String sessionUid) {
        this.sessionUid = sessionUid;
    }
}
