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
import ru.sgnhp.dao.ITaskDao;
import ru.sgnhp.domain.TaskBean;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 *
 * @author 48han
 */
public class TaskDaoImplTest extends AbstractTransactionalDataSourceSpringContextTests {

    private ITaskDao taskDao;

    public TaskDaoImplTest() {
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
    public void testSaveTask() {
        TaskBean task = new TaskBean();
        task.setInternalNumber(1);
        task.setExternalNumber("ВН-1101/74");
        task.setDescription("Для рассмотрения и принятия решения");
        Calendar cal = Calendar.getInstance();
        Date d = new Date(cal.getTime().getTime());
        //task.setStartDate(d);
        cal.add(Calendar.DATE, 30);
        d = new Date(cal.getTime().getTime());
        //task.setDueDate(d);
        taskDao.saveTask(task);
    }

    @Test
    public void testUpdateTask() {
    }

    @Test
    public void testCloseTask() {
    }

    @Test
    public void testGetTaskByUid() {
    }

    @Test
    public void testAssignTaskToUser() {
    }

    @Test
    public void testGetTasksByUser() {
    }

    @Test
    public void testGetTaskNewNumber() {
        assertEquals("T.2009-1", taskDao.getTaskNewNumber());
    }

    /**
     * @param taskDao the taskDao to set
     */
    public void setTaskDao(ITaskDao taskDao) {
        this.taskDao = taskDao;
    }
}