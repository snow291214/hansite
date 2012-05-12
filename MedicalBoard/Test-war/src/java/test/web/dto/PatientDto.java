package test.web.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Khudyakov Alexey
 * Skype: khudyakov.alexey
 * Email: khudyakov.alexey@gmail.com
 * 
 */
public class PatientDto implements Serializable {
    private static final long serialVersionUID = 4L;
    private String login;
    private String postOfSigner;
    private String hrSpecialist;
    private String hrManager;
    private String hrEmail;
    private int medialogCode;
    private String companyName;
    private String formOfProperty;
    private String okved;
    private String departmentName;
    private String departmentCode;
    private String specialityName;
    private String specialityCode;
    private String post;
    private String labourCondition;
    private List<HazardDto> hazards;
    private String lastName;
    private String patronymicName;
    private String firstName;
    private String birthday;
    private String sex;
    
    private String residenceCity;
    private String residenceStreet;
    private String residenceHome;
    private String residenceFlat;
    private String mobilePhone;
    
    private String omsCertificateNumber;
    private String omsCertificateCompany;
    private String omsCertificateDate;
    private String inn;
    private String snils;
    
    private String passportSeries;
    private String passportNumber;
    private String passportIssuer;
    private String passportDateOfIssue;


    /**
     * @return the medialogCode
     */
    public int getMedialogCode() {
        return medialogCode;
    }

    /**
     * @param medialogCode the medialogCode to set
     */
    public void setMedialogCode(int medialogCode) {
        this.medialogCode = medialogCode;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the departmentCode
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * @param departmentCode the departmentCode to set
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    /**
     * @return the specialityName
     */
    public String getSpecialityName() {
        return specialityName;
    }

    /**
     * @param specialityName the specialityName to set
     */
    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    /**
     * @return the specialityCode
     */
    public String getSpecialityCode() {
        return specialityCode;
    }

    /**
     * @param specialityCode the specialityCode to set
     */
    public void setSpecialityCode(String specialityCode) {
        this.specialityCode = specialityCode;
    }

    /**
     * @return the post
     */
    public String getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * @return the labourCondition
     */
    public String getLabourCondition() {
        return labourCondition;
    }

    /**
     * @param labourCondition the labourCondition to set
     */
    public void setLabourCondition(String labourCondition) {
        this.labourCondition = labourCondition;
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
     * @return the hrManager
     */
    public String getHrManager() {
        return hrManager;
    }

    /**
     * @param hrManager the hrManager to set
     */
    public void setHrManager(String hrManager) {
        this.hrManager = hrManager;
    }

    /**
     * @return the hrEmail
     */
    public String getHrEmail() {
        return hrEmail;
    }

    /**
     * @param hrEmail the hrEmail to set
     */
    public void setHrEmail(String hrEmail) {
        this.hrEmail = hrEmail;
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
     * @return the patronymicName
     */
    public String getPatronymicName() {
        return patronymicName;
    }

    /**
     * @param patronymicName the patronymicName to set
     */
    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the hazards
     */
    public List<HazardDto> getHazards() {
        return hazards;
    }

    /**
     * @param hazards the hazards to set
     */
    public void setHazards(List<HazardDto> hazards) {
        this.hazards = hazards;
    }

    /**
     * @return the residenceCity
     */
    public String getResidenceCity() {
        return residenceCity;
    }

    /**
     * @param residenceCity the residenceCity to set
     */
    public void setResidenceCity(String residenceCity) {
        this.residenceCity = residenceCity;
    }

    /**
     * @return the residenceStreet
     */
    public String getResidenceStreet() {
        return residenceStreet;
    }

    /**
     * @param residenceStreet the residenceStreet to set
     */
    public void setResidenceStreet(String residenceStreet) {
        this.residenceStreet = residenceStreet;
    }

    /**
     * @return the residenceHome
     */
    public String getResidenceHome() {
        return residenceHome;
    }

    /**
     * @param residenceHome the residenceHome to set
     */
    public void setResidenceHome(String residenceHome) {
        this.residenceHome = residenceHome;
    }

    /**
     * @return the residenceFlat
     */
    public String getResidenceFlat() {
        return residenceFlat;
    }

    /**
     * @param residenceFlat the residenceFlat to set
     */
    public void setResidenceFlat(String residenceFlat) {
        this.residenceFlat = residenceFlat;
    }

    /**
     * @return the mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone the mobilePhone to set
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * @return the passportSeries
     */
    public String getPassportSeries() {
        return passportSeries;
    }

    /**
     * @param passportSeries the passportSeries to set
     */
    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    /**
     * @return the passportNumber
     */
    public String getPassportNumber() {
        return passportNumber;
    }

    /**
     * @param passportNumber the passportNumber to set
     */
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * @return the passportIssuer
     */
    public String getPassportIssuer() {
        return passportIssuer;
    }

    /**
     * @param passportIssuer the passportIssuer to set
     */
    public void setPassportIssuer(String passportIssuer) {
        this.passportIssuer = passportIssuer;
    }

    /**
     * @return the passportDateOfIssue
     */
    public String getPassportDateOfIssue() {
        return passportDateOfIssue;
    }

    /**
     * @param passportDateOfIssue the passportDateOfIssue to set
     */
    public void setPassportDateOfIssue(String passportDateOfIssue) {
        this.passportDateOfIssue = passportDateOfIssue;
    }

    /**
     * @return the omsCertificateNumber
     */
    public String getOmsCertificateNumber() {
        return omsCertificateNumber;
    }

    /**
     * @param omsCertificateNumber the omsCertificateNumber to set
     */
    public void setOmsCertificateNumber(String omsCertificateNumber) {
        this.omsCertificateNumber = omsCertificateNumber;
    }

    /**
     * @return the omsCertificateCompany
     */
    public String getOmsCertificateCompany() {
        return omsCertificateCompany;
    }

    /**
     * @param omsCertificateCompany the omsCertificateCompany to set
     */
    public void setOmsCertificateCompany(String omsCertificateCompany) {
        this.omsCertificateCompany = omsCertificateCompany;
    }

    /**
     * @return the omsCertificateDate
     */
    public String getOmsCertificateDate() {
        return omsCertificateDate;
    }

    /**
     * @param omsCertificateDate the omsCertificateDate to set
     */
    public void setOmsCertificateDate(String omsCertificateDate) {
        this.omsCertificateDate = omsCertificateDate;
    }

    /**
     * @return the inn
     */
    public String getInn() {
        return inn;
    }

    /**
     * @param inn the inn to set
     */
    public void setInn(String inn) {
        this.inn = inn;
    }

    /**
     * @return the snils
     */
    public String getSnils() {
        return snils;
    }

    /**
     * @param snils the snils to set
     */
    public void setSnils(String snils) {
        this.snils = snils;
    }

    /**
     * @return the formOfProperty
     */
    public String getFormOfProperty() {
        return formOfProperty;
    }

    /**
     * @param formOfProperty the formOfProperty to set
     */
    public void setFormOfProperty(String formOfProperty) {
        this.formOfProperty = formOfProperty;
    }

    /**
     * @return the okved
     */
    public String getOkved() {
        return okved;
    }

    /**
     * @param okved the okved to set
     */
    public void setOkved(String okved) {
        this.okved = okved;
    }

    /**
     * @return the hrSpecialist
     */
    public String getHrSpecialist() {
        return hrSpecialist;
    }

    /**
     * @param hrSpecialist the hrSpecialist to set
     */
    public void setHrSpecialist(String hrSpecialist) {
        this.hrSpecialist = hrSpecialist;
    }

    /**
     * @return the postOfSigner
     */
    public String getPostOfSigner() {
        return postOfSigner;
    }

    /**
     * @param postOfSigner the postOfSigner to set
     */
    public void setPostOfSigner(String postOfSigner) {
        this.postOfSigner = postOfSigner;
    }
}
