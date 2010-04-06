/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.service.impl;

import java.text.ParseException;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.domain.DocumentBean;
import ru.sgnhp.domain.DocumentTypeBean;
import ru.sgnhp.service.IDocumentService;
import ru.sgnhp.service.IDocumentTypeService;

/**
 *
 * @author 48han
 */
public class DocumentServiceImplTest extends AbstractTransactionalDataSourceSpringContextTests {

    private IDocumentService documentService;
    private IDocumentTypeService documentTypeService;

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

    @Test
    public void testGetAllDocumentsByYear() throws ParseException {
        DocumentTypeBean documentTypeBean = documentTypeService.get(1L);
        assertNotNull(documentService.getAllDocumentsByYear(2010, documentTypeBean));
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
}
