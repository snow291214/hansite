package ru.salavatmed.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * Khudyakov Alexey
 * Skype: khudyakov.alexey
 * Email: khudyakov.alexey@gmail.com
 * 
 */
@Entity
@Table(name = "hazards", catalog = "dashboard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hazard.findAll", query = "SELECT h FROM Hazard h"),
    @NamedQuery(name = "Hazard.findByUid", query = "SELECT h FROM Hazard h WHERE h.uid = :uid"),
    @NamedQuery(name = "Hazard.findByAppendix", query = "SELECT h FROM Hazard h WHERE h.appendix = :appendix"),
    @NamedQuery(name = "Hazard.findByParagraph", query = "SELECT h FROM Hazard h WHERE h.paragraph = :paragraph"),
    @NamedQuery(name = "Hazard.findByAppendixAndParagraph", query = "SELECT h FROM Hazard h WHERE h.appendix = :appendix and h.paragraph = :paragraph"),
    @NamedQuery(name = "Hazard.findByName", query = "SELECT h FROM Hazard h WHERE h.name = :name")})
public class Hazard implements Serializable {
    private static final long serialVersionUID = 3L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "Uid", nullable = false)
    private Integer uid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Appendix", nullable = false)
    private int appendix;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Paragraph", nullable = false, length = 10)
    private String paragraph;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    public Hazard() {
    }

    public Hazard(Integer uid) {
        this.uid = uid;
    }

    public Hazard(Integer uid, int appendix, String paragraph, String name) {
        this.uid = uid;
        this.appendix = appendix;
        this.paragraph = paragraph;
        this.name = name;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public int getAppendix() {
        return appendix;
    }

    public void setAppendix(int appendix) {
        this.appendix = appendix;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Hazard)) {
            return false;
        }
        Hazard other = (Hazard) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.salavatmed.entity.Hazard[ uid=" + uid + " ]";
    }
    
}
