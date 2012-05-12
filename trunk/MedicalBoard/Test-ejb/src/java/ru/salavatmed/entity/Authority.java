/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.salavatmed.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 77han
 */
@Entity
@Table(name = "authorities", catalog = "dashboard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Authority.findAll", query = "SELECT a FROM Authority a"),
    @NamedQuery(name = "Authority.findByUid", query = "SELECT a FROM Authority a WHERE a.uid = :uid"),
    @NamedQuery(name = "Authority.findByAuthority", query = "SELECT a FROM Authority a WHERE a.authority = :authority"),
    @NamedQuery(name = "Authority.findByMedialogCode", query = "SELECT a FROM Authority a WHERE a.medialogCode = :medialogCode"),
    @NamedQuery(name = "Authority.findByFullName", query = "SELECT a FROM Authority a WHERE a.fullName = :fullName"),
    @NamedQuery(name = "Authority.findByFormOfProperty", query = "SELECT a FROM Authority a WHERE a.formOfProperty = :formOfProperty"),
    @NamedQuery(name = "Authority.findByOkved", query = "SELECT a FROM Authority a WHERE a.okved = :okved"),
    @NamedQuery(name = "Authority.findByOKVEDName", query = "SELECT a FROM Authority a WHERE a.oKVEDName = :oKVEDName"),
    @NamedQuery(name = "Authority.findByManager", query = "SELECT a FROM Authority a WHERE a.manager = :manager"),
    @NamedQuery(name = "Authority.findByHRManager", query = "SELECT a FROM Authority a WHERE a.hRManager = :hRManager"),
    @NamedQuery(name = "Authority.findByIsDirectorSigner", query = "SELECT a FROM Authority a WHERE a.isDirectorSigner = :isDirectorSigner")})
public class Authority implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "Uid", nullable = false)
    private Integer uid;
    @Size(max = 50)
    @Column(name = "Authority", length = 50)
    private String authority;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MedialogCode", nullable = false)
    private int medialogCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FullName", nullable = false, length = 255)
    private String fullName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FormOfProperty", nullable = false, length = 100)
    private String formOfProperty;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "OKVED", nullable = false, length = 50)
    private String okved;
    @Size(max = 255)
    @Column(name = "OKVEDName", length = 255)
    private String oKVEDName;
    @Size(max = 200)
    @Column(name = "Manager", length = 200)
    private String manager;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "HRManager", nullable = false, length = 150)
    private String hRManager;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsDirectorSigner", nullable = false)
    private short isDirectorSigner;

    public Authority() {
    }

    public Authority(Integer uid) {
        this.uid = uid;
    }

    public Authority(Integer uid, int medialogCode, String fullName, String formOfProperty, String okved, String hRManager, short isDirectorSigner) {
        this.uid = uid;
        this.medialogCode = medialogCode;
        this.fullName = fullName;
        this.formOfProperty = formOfProperty;
        this.okved = okved;
        this.hRManager = hRManager;
        this.isDirectorSigner = isDirectorSigner;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public int getMedialogCode() {
        return medialogCode;
    }

    public void setMedialogCode(int medialogCode) {
        this.medialogCode = medialogCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFormOfProperty() {
        return formOfProperty;
    }

    public void setFormOfProperty(String formOfProperty) {
        this.formOfProperty = formOfProperty;
    }

    public String getOkved() {
        return okved;
    }

    public void setOkved(String okved) {
        this.okved = okved;
    }

    public String getOKVEDName() {
        return oKVEDName;
    }

    public void setOKVEDName(String oKVEDName) {
        this.oKVEDName = oKVEDName;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getHRManager() {
        return hRManager;
    }

    public void setHRManager(String hRManager) {
        this.hRManager = hRManager;
    }

    public short getIsDirectorSigner() {
        return isDirectorSigner;
    }

    public void setIsDirectorSigner(short isDirectorSigner) {
        this.isDirectorSigner = isDirectorSigner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authority)) {
            return false;
        }
        Authority other = (Authority) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.salavatmed.entity.Authority[ uid=" + uid + " ]";
    }
    
}
