/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.dao.impl;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Test;
import ru.sgnhp.dao.ITaskDao;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.FileBean;
import ru.sgnhp.domain.TaskBean;

/**
 *
 * @author 48han
 */
public class TaskDaoImplTest extends AbstractTransactionalDataSourceSpringContextTests {

    private ITaskDao taskDao;

    public TaskDaoImplTest() {
    }

//    @Test
//    public void testSaveTask() throws IOException {
//        TaskBean taskBean = new TaskBean();
//        taskBean.setInternalNumber(101);
//        taskBean.setIncomingNumber(102);
//        taskBean.setDescription("Test task");
//        taskBean.setStartDate(DateUtils.nowDate());
//        taskBean.setDueDate(DateUtils.increaseDate(DateUtils.nowDate(), 3));
//        taskBean.setExternalAssignee("EA");
//        taskBean.setExternalCompany("EC");
//
//        FileBean fileBean = new FileBean();
//        fileBean.setBlobField(null);
//        fileBean.setFileName("102.pdf");
//        fileBean.setTaskUid(taskBean);
//
//        Set<FileBean> fileBeans = new LinkedHashSet<FileBean>();
//        fileBeans.add(fileBean);
//
//        taskDao.save(taskBean);
//    }

    @Test
    public void testGetTaskByInternalNumber() {
        assertNotNull(taskDao.getTaskByInternalNumber(101));
    }

    @Test
    public void testGetTaskByExternalNumber() {
        assertNotNull(taskDao.getTaskByExternalNumber("ИСХ-061-02-35134"));
    }

//    @Test
//    public void testGetTaskByIncomingNumber() {
//        assertNotNull(taskDao.getTaskByIncomingNumber(619));
//    }

    @Test
    public void testGetTasksByExternalAssignee() {
        assertNotNull(taskDao.getTasksByExternalAssignee("%Магафуров%"));
    }

    @Test
    public void testGetTasksByDescription() {
        assertNotNull(taskDao.getTasksByDescription("%Медсервис%"));
    }

    @Test
    public void testGetNewInternalNumber() {
        assertEquals(1367, taskDao.getNewInternalNumber());
    }

    @Test
    public void testGetNewIncomingNumber() {
        assertEquals(444, taskDao.getNewIncomingNumber());
    }

    @Test
    public void testGetfiles() {
        TaskBean taskBean = taskDao.get(12L);
        assertNotNull(taskBean.getFilesSet());
    }

    @Test
    public void testGetPrimaveraUid() {
        assertNotNull(taskDao.getDistinctPrimaveraIDS());
    }

    public void setTaskDao(ITaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }
}
