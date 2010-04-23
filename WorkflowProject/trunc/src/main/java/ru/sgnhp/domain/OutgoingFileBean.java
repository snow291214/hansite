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
import org.apache.log4j.Logger;
import org.hibernate.annotations.ForeignKey;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
@Entity
@Table(name = "outgoingfiles", catalog = "workflowdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "OutgoingFileBean.findAll", query = "SELECT o FROM OutgoingFileBean o"),
    @NamedQuery(name = "OutgoingFileBean.findByUid", query = "SELECT o FROM OutgoingFileBean o WHERE o.uid = :uid"),
    @NamedQuery(name = "OutgoingFileBean.findByFileName", query = "SELECT o FROM OutgoingFileBean o WHERE o.fileName = :fileName")})
public class OutgoingFileBean implements Serializable {

    private static final long serialVersionUID = 6L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Basic(optional = false)
    @Column(name = "FileName", nullable = false, length = 100)
    private String fileName;
    @Column(name = "FilePath")
    private String filePath;
//    @Basic(optional = false, fetch=FetchType.LAZY)
//    @Lob
//    @Column(name = "BlobField", columnDefinition = "LONGBLOB")
    @Transient
    private byte[] blobField;
    @ForeignKey(name = "fk_outgoingmail")
    @JoinColumn(name = "OutgoingMailUid", referencedColumnName = "Uid", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private OutgoingMailBean outgoingMailBean;

    public OutgoingFileBean() {
    }

    public OutgoingFileBean(Long uid) {
        this.uid = uid;
    }

    public OutgoingFileBean(Long uid, String fileName, byte[] blobField) {
        this.uid = uid;
        this.fileName = fileName;
        this.blobField = blobField;
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

    private static String getYearFromDate(Date date) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
        return simpleDateformat.format(date);
    }

    private static String getMonthFromDate(Date date) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("MM");
        return simpleDateformat.format(date);
    }

    public byte[] getBlobField() throws IOException {
        Properties pro = new Properties();
        pro.load(this.getClass().getResourceAsStream("/general.properties"));
        String fp = pro.getProperty("repository.repositoryPath");
        blobField = this.getBytesFromFile(new File(fp + this.getFilePath()));
        return blobField;
    }

    public void setBlobField(byte[] blobField) throws IOException {
        if (blobField == null) {
            this.blobField = null;
        }
        Logger logger = Logger.getRootLogger();
        Properties pro = new Properties();
        pro.load(this.getClass().getResourceAsStream("/general.properties"));
        String repositoryPath = pro.getProperty("repository.repositoryPath");
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
                OutgoingMailBean b = this.getOutgoingMailBean();
        logger.warn("!!!" + this.getOutgoingMailBean());
        String path = "/OutgoingMailFiles/" + getYearFromDate(this.getOutgoingMailBean().getOutgoingDate())
                + "/" + getMonthFromDate(this.getOutgoingMailBean().getOutgoingDate())
                + "/" + fmt.format(this.getOutgoingMailBean().getOutgoingDate()) + "/"
                + this.getOutgoingMailBean().getUid().toString();

        File directory = new File(repositoryPath + path);
        if (!directory.exists()) {
            logger.info("creating directory: " + repositoryPath + path);
            directory.mkdirs();
        }

        FileOutputStream fos = new FileOutputStream(repositoryPath + path + "/" + this.getFileName());
        fos.write(blobField);//1645
        fos.close();
        this.setFilePath(path + "/" + this.getFileName());

        this.blobField = blobField;
    }

    public OutgoingMailBean getOutgoingMailBean() {
        return outgoingMailBean;
    }

    public void setOutgoingMailBean(OutgoingMailBean outgoingMailBean) {
        this.outgoingMailBean = outgoingMailBean;
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
        if (!(object instanceof OutgoingFileBean)) {
            return false;
        }
        OutgoingFileBean other = (OutgoingFileBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.OutgoingFileBean[uid=" + uid + "]";
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
