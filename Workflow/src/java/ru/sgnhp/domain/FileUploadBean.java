package ru.sgnhp.domain;

import java.io.InputStream;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */

public class FileUploadBean {
    private Long uid;
    private Long taskUid;
    private String fileName;
    private InputStream contentStream;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getTaskUid() {
        return taskUid;
    }

    public void setTaskUid(Long taskUid) {
        this.taskUid = taskUid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getContentStream() {
        return contentStream;
    }

    public void setContentStream(InputStream contentStream) {
        this.contentStream = contentStream;
    }
}


