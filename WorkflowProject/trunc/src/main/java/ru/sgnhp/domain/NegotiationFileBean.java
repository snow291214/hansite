package ru.sgnhp.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author 77han
 */
@Entity
@Table(name = "negotiation_files", catalog = "workflowdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NegotiationFileBean.findAll", query = "SELECT n FROM NegotiationFileBean n"),
    @NamedQuery(name = "NegotiationFileBean.findByUid", query = "SELECT n FROM NegotiationFileBean n WHERE n.uid = :uid"),
    @NamedQuery(name = "NegotiationFileBean.findByFileName", query = "SELECT n FROM NegotiationFileBean n WHERE n.fileName = :fileName"),
    @NamedQuery(name = "NegotiationFileBean.findByFilePath", query = "SELECT n FROM NegotiationFileBean n WHERE n.filePath = :filePath")})
public class NegotiationFileBean implements Serializable {

    private static final long serialVersionUID = 280620114L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Basic(optional = false)
    @Column(name = "FileName", nullable = false, length = 100)
    private String fileName;
    @Column(name = "FilePath", length = 255)
    private String filePath;
    @ForeignKey(name = "fk_negotiation_files_negotiations")
    @JoinColumn(name = "NegotiationUid", referencedColumnName = "Uid")
    @ManyToOne(fetch = FetchType.LAZY)
    private NegotiationBean negotiationBean;
    @Transient
    private byte[] blobField;
    
    public NegotiationFileBean() {
    }

    public NegotiationFileBean(Long uid) {
        this.uid = uid;
    }

    public NegotiationFileBean(Long uid, String fileName) {
        this.uid = uid;
        this.fileName = fileName;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
        if (!(object instanceof NegotiationFileBean)) {
            return false;
        }
        NegotiationFileBean other = (NegotiationFileBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.NegotiationFileBean[ uid=" + uid + " ]";
    }

    /**
     * @return the negotiationBean
     */
    public NegotiationBean getNegotiationBean() {
        return negotiationBean;
    }

    /**
     * @param negotiationBean the negotiationBean to set
     */
    public void setNegotiationBean(NegotiationBean negotiationBean) {
        this.negotiationBean = negotiationBean;
    }

    /**
     * @return the blobField
     */
    public byte[] getBlobField() throws FileNotFoundException, IOException  {
        Properties pro = new Properties();
        pro.load(this.getClass().getResourceAsStream("/general.properties"));
        String fp = pro.getProperty("repository.repositoryPath");
        blobField = this.getBytesFromFile(new File(fp + this.filePath));
        return blobField;
    }

    /**
     * @param blobField the blobField to set
     */
    public void setBlobField(byte[] blobField) throws IOException {
        if (blobField == null) {
            this.blobField = null;
        }
        Properties pro = new Properties();
        pro.load(this.getClass().getResourceAsStream("/general.properties"));
        String repositoryPath = pro.getProperty("repository.repositoryPath");
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        String path = "/Negotiations/"
                + this.getNegotiationBean().getNegotiationTypeBean().getUid()
                + "/" + getYearFromDate(this.getNegotiationBean().getStartDate())
                + "/" + getMonthFromDate(this.getNegotiationBean().getStartDate())
                + "/" + fmt.format(this.getNegotiationBean().getStartDate()) + "/"
                + this.getNegotiationBean().getUid().toString();

        File directory = new File(repositoryPath + path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        FileOutputStream fos = new FileOutputStream(repositoryPath + path + "/" + this.getFileName());
        fos.write(blobField);//1645
        fos.close();
        this.setFilePath(path + "/" + this.getFileName());

        this.blobField = blobField;
    }
    
    private static String getYearFromDate(Date date) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
        return simpleDateformat.format(date);
    }

    private static String getMonthFromDate(Date date) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("MM");
        return simpleDateformat.format(date);
    }
    
    private byte[] getBytesFromFile(File file) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
}
