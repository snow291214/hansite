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
import java.util.logging.Logger;
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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.ForeignKey;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
@Entity
@Table(name = "files", catalog = "workflowdb", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"Uid"})})
@NamedQueries({
    @NamedQuery(name = "FileBean.findAll", query = "SELECT f FROM FileBean f"),
    @NamedQuery(name = "FileBean.findByUid", query = "SELECT f FROM FileBean f WHERE f.uid = :uid"),
    @NamedQuery(name = "FileBean.findByFileName", query = "SELECT f FROM FileBean f WHERE f.fileName = :fileName")
})
public class FileBean implements Serializable {

    private static final long serialVersionUID = 5L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Column(name = "FileName", length = 100)
    private String fileName;
    @Column(name = "FilePath")
    private String filePath;
    //@Lob
    //@Basic(fetch=FetchType.LAZY)
    //@Column(name = "BlobField", columnDefinition = "LONGBLOB")
    @Transient
    private byte[] blobField;
    @ForeignKey(name = "fk_tasks")
    @JoinColumn(name = "TaskUid", referencedColumnName = "Uid")
    @ManyToOne(fetch = FetchType.LAZY)
    private TaskBean tasks;

    public FileBean() {
    }

    public FileBean(Long uid) {
        this.uid = uid;
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
        Logger logger = Logger.getLogger("FileBean");
        Properties pro = new Properties();
        pro.load(this.getClass().getResourceAsStream("/general.properties"));
        String repositoryPath = pro.getProperty("repository.repositoryPath");
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        String path = "/TaskFiles/" + getYearFromDate(this.getTaskUid().getStartDate())
                + "/" + getMonthFromDate(this.getTaskUid().getStartDate())
                + "/" + fmt.format(this.getTaskUid().getStartDate()) + "/"
                + this.getTaskUid().getUid().toString();

        File directory = new File(repositoryPath + path);
        if (!directory.exists()) {
            //logger.info("creating directory: " + repositoryPath + path);
            directory.mkdirs();
        }

        FileOutputStream fos = new FileOutputStream(repositoryPath + path + "/" + this.getFileName());
        fos.write(blobField);//1645
        fos.close();
        this.setFilePath(path + "/" + this.getFileName());

        this.blobField = blobField;
    }

    public TaskBean getTaskUid() {
        return tasks;
    }

    public void setTaskUid(TaskBean taskUid) {
        this.tasks = taskUid;
    }

    private static String getYearFromDate(Date date) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
        return simpleDateformat.format(date);
    }

    private static String getMonthFromDate(Date date) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("MM");
        return simpleDateformat.format(date);
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
        if (!(object instanceof FileBean)) {
            return false;
        }
        FileBean other = (FileBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.Files[uid=" + uid + "]";
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
