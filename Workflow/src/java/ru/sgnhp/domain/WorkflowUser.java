package ru.sgnhp.domain;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */

public class WorkflowUser {

    private Long uid;
    private String login;
    private String lastName;
    private String middleName;
    private String firstName;
    private String email;
    private Long groupUid;

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
}
