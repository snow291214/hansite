package ru.sgnhp.dao.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.DateUtils;
import ru.sgnhp.dao.IDocumentDao;
import ru.sgnhp.dao.IDocumentTypeDao;
import ru.sgnhp.dao.IUserDao;
import ru.sgnhp.domain.DocumentBean;
import ru.sgnhp.domain.DocumentFileBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class DocumentDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    public DocumentDaoTest() {
    }
    private IDocumentDao documentDao;
    private IDocumentTypeDao documentTypeDao;
    private IUserDao userDao;

    @Test
    public void testAddNewDocument() {
        DocumentBean documentBean = new DocumentBean();
        documentBean.setDocumentTypeBean(documentTypeDao.get(1L));
        documentBean.setDocumentNumber(1);
        documentBean.setDocumentDate(DateUtils.nowDate());
        documentBean.setDescription("Test");

        DocumentFileBean documentFileBean = new DocumentFileBean();
        documentFileBean.setFileName("test.pdf");
        documentFileBean.setBlobField(null);
        documentFileBean.setDocumentBean(documentBean);
        Set<DocumentFileBean> fileBeans = new LinkedHashSet<DocumentFileBean>();

        documentBean.setDocumentFileBeanSet(fileBeans);
        documentBean.setContactPerson(userDao.get(72L));
        documentBean.setControlPerson(userDao.get(72L));

        assertNotNull(documentDao.save(documentBean));
    }

    @Test
    public void testFindByDocumentNumber() {
//        assertNotNull(documentDao.getAll());
//        List<DocumentBean> documentBeans = documentDao.findByDocumentNumber(null, null);
//        for (DocumentBean b : documentBeans){
//            System.out.println(b.getDescription());
//        }
    }

    @Test
    public void testGetNewDocumentNumber() {
        assertEquals(documentDao.getNewDocumentNumber(2010, 2L),1);
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    public IDocumentDao getDocumentDao() {
        return documentDao;
    }

    public void setDocumentDao(IDocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    public IDocumentTypeDao getDocumentTypeDao() {
        return documentTypeDao;
    }

    public void setDocumentTypeDao(IDocumentTypeDao documentTypeDao) {
        this.documentTypeDao = documentTypeDao;
    }


    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
