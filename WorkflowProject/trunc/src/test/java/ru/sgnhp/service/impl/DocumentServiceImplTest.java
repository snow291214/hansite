/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.domain.DocumentBean;
import ru.sgnhp.domain.DocumentFileBean;
import ru.sgnhp.domain.DocumentTypeBean;
import ru.sgnhp.service.IDocumentFileService;
import ru.sgnhp.service.IDocumentService;
import ru.sgnhp.service.IDocumentTypeService;

/**
 *
 * @author 48han
 */
public class DocumentServiceImplTest extends AbstractTransactionalDataSourceSpringContextTests {

    private IDocumentService documentService;
    private IDocumentTypeService documentTypeService;
    private IDocumentFileService documentFileService;

    public DocumentServiceImplTest() {
    }

    @Test
    public void testFindByDocumentNumber() {
    }

    @Test
    public void testFindByDocumentDate() {
    }

    @Test
    public void testGetNewDocumentNumber() {
    }

//    @Test
//    public void testGetAllDocumentsByYear() throws ParseException {
//        DocumentTypeBean documentTypeBean = documentTypeService.get(1L);
//        assertNotNull(documentService.getAllDocumentsByYear(2010, documentTypeBean));
//    }
    @Test
    public void testDownloadAllBinaryData() throws IOException {
//        Properties pro = new Properties();
//        pro.load(this.getClass().getResourceAsStream("/general.properties"));
//        String repositoryPath = pro.getProperty("repository.repositoryPath");
        //List<DocumentBean> documents = documentService.getAll();
        String repositoryPath = "C:/Temp";
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");

        DocumentTypeBean type = documentTypeService.get(1L);

        for (DocumentBean documentBean : type.getDocumentBeanSet()) {
            String path = "/Documents/"
                    + documentBean.getDocumentTypeBean().getUid()
                    + "/" + getYearFromDate(documentBean.getDocumentDate())
                    + "/" + getMonthFromDate(documentBean.getDocumentDate())
                    + "/" + fmt.format(documentBean.getDocumentDate()) + "/"
                    + documentBean.getUid().toString();

            File directory = new File(repositoryPath + path);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            for (DocumentFileBean documentFile : documentBean.getDocumentFileBeanSet()) {
                FileOutputStream fos = new FileOutputStream(repositoryPath + path + "/" + documentFile.getFileName());
                fos.write(documentFile.getBlobField());//1645
                fos.close();
                System.out.println("update document_files set FilePath ='" + path + "/" + documentFile.getFileName() + "' where uid = " + documentFile.getUid()+";");
//                documentFile.setFilePath(path);
//                documentFileService.save(documentFile);
            }
        }
    }

    private static String getYearFromDate(Date date) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
        return simpleDateformat.format(date);
    }

    private static String getMonthFromDate(Date date) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("MM");
        return simpleDateformat.format(date);
    }

    public IDocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(IDocumentService documentService) {
        this.documentService = documentService;
    }

    public IDocumentTypeService getDocumentTypeService() {
        return documentTypeService;
    }

    public void setDocumentTypeService(IDocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    /**
     * @return the documentFileService
     */
    public IDocumentFileService getDocumentFileService() {
        return documentFileService;
    }

    /**
     * @param documentFileService the documentFileService to set
     */
    public void setDocumentFileService(IDocumentFileService documentFileService) {
        this.documentFileService = documentFileService;
    }
}
