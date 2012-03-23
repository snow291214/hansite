package ru.sgnhp.dto;

/**
 *
 * Khudyakov Alexey Skype: khudyakov.alexey Email: khudyakov.alexey@gmail.com
 *
 */
public class DocumentFileDto {

    private static final long serialVersionUID = 41L;
    private String fileName;
    private byte[] blobField;
    private String filePath;

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the blobField
     */
    public byte[] getBlobField() {
        return blobField;
    }

    /**
     * @param blobField the blobField to set
     */
    public void setBlobField(byte[] blobField) {
        this.blobField = blobField;
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
