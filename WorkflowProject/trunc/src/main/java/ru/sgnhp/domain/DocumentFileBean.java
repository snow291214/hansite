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
import org.hibernate.annotations.ForeignKey;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
@Entity
@Table(name = "document_files", catalog = "workflowdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "DocumentFileBean.findAll", query = "SELECT d FROM DocumentFileBean d"),
    @NamedQuery(name = "DocumentFileBean.findByUid", query = "SELECT d FROM DocumentFileBean d WHERE d.uid = :uid"),
    @NamedQuery(name = "DocumentFileBean.findByFileName", query = "SELECT d FROM DocumentFileBean d WHERE d.fileName = :fileName")})
public class DocumentFileBean implements Serializable {

    private static final long serialVersionUID = 11L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false, columnDefinition = "INTEGER(11)")
    private Long uid;
    @Basic(optional = false)
    @Column(name = "FileName", nullable = false, length = 100)
    private String fileName;
    @Basic(optional = false, fetch = FetchType.LAZY)
//    @Lob
//    @Column(name = "BlobField", nullable = false, columnDefinition = "LONGBLOB")
//    private byte[] blobField;
    @Transient
    private byte[] blobField;
    @ForeignKey(name = "fk_document_document_files")
    @JoinColumn(name = "DocumentUid", referencedColumnName = "Uid", nullable = false, columnDefinition = "INTEGER(11)")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DocumentBean documentBean;
    @Column(name = "FilePath")
    private String filePath;

    public DocumentFileBean() {
    }

    public DocumentFileBean(Long uid) {
        this.uid = uid;
    }

    public DocumentFileBean(Long uid, String fileName, byte[] blobField) {
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

//    public byte[] getBlobField() {
//        return blobField;
//    }
//
//    public void setBlobField(byte[] blobField) {
//        this.blobField = blobField;
//    }
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

    public byte[] getBlobField() throws FileNotFoundException, IOException {
        Properties pro = new Properties();
        pro.load(this.getClass().getResourceAsStream("/general.properties"));
        String fp = pro.getProperty("repository.repositoryPath");
        blobField = this.getBytesFromFile(new File(fp + this.filePath));
        return blobField;
    }

    public void setBlobField(byte[] blobField) throws IOException {
        if (blobField == null) {
            this.blobField = null;
        }
        Properties pro = new Properties();
        pro.load(this.getClass().getResourceAsStream("/general.properties"));
        String repositoryPath = pro.getProperty("repository.repositoryPath");
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        String path = "/Documents/"
                + this.documentBean.getDocumentTypeBean().getUid()
                + "/" + getYearFromDate(this.documentBean.getDocumentDate())
                + "/" + getMonthFromDate(this.documentBean.getDocumentDate())
                + "/" + fmt.format(this.documentBean.getDocumentDate()) + "/"
                + this.documentBean.getUid().toString();

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) { // TODO: Warning - this method won't work in the case the id fields are not set

        if (!(object instanceof DocumentFileBean)) {
            return false;
        }
        DocumentFileBean other = (DocumentFileBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.DocumentFileBean[uid=" + uid + "]";
    }

    public DocumentBean getDocumentBean() {
        return documentBean;
    }

    public void setDocumentBean(DocumentBean documentBean) {
        this.documentBean = documentBean;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
