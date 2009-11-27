/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.dao.impl;

import java.sql.Date;
import java.util.Calendar;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.dao.IWorkflowDao;
import ru.sgnhp.domain.WorkflowBean;

/**
 *
 * @author 48han
 */
public class WorkflowDaoImplTest extends AbstractTransactionalDataSourceSpringContextTests {

    private IWorkflowDao workflowDao;

    public WorkflowDaoImplTest() {
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testSaveWorkflow() {
        WorkflowBean wf = new WorkflowBean();
        wf.setTaskUid(Long.parseLong("1"));
        wf.setParentUserUid(Long.parseLong("19"));
        wf.setUserUid(Long.parseLong("18"));
        wf.setDescription("Для рассмотрения");
        wf.setState("0");
        Calendar cal = Calendar.getInstance();
        Date d = new Date(cal.getTime().getTime());
        //wf.setAssignDate(d);
        cal.add(Calendar.DATE, 30);
        d = new Date(cal.getTime().getTime());
        //wf.setFinishDate(d);
        workflowDao.saveWorkflow(wf);
    }

    public void setWorkflowDao(IWorkflowDao workflowDao) {
        this.workflowDao = workflowDao;
    }

    /**
     * @param workflowDao the workflowDao to set
     */
}