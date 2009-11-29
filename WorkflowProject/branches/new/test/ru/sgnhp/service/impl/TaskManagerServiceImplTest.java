/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.FileBean;
import ru.sgnhp.domain.TaskBean;
import ru.sgnhp.service.ITaskManagerService;

/**
 *
 * @author 48han
 */
public class TaskManagerServiceImplTest extends AbstractTransactionalDataSourceSpringContextTests {

    private ITaskManagerService taskManagerService;

    public TaskManagerServiceImplTest() {
    }

    @Test
    public void saveTask() {
        TaskBean taskBean = new TaskBean();
        taskBean.setInternalNumber(101);
        taskBean.setIncomingNumber(102);
        taskBean.setDescription("Test task");
        taskBean.setStartDate(DateUtils.nowDate());
        taskBean.setDueDate(DateUtils.increaseDate(DateUtils.nowDate(), 3));
        taskBean.setExternalAssignee("EA");
        taskBean.setExternalCompany("EC");

        FileBean fileBean = new FileBean();
        fileBean.setBlobField(null);
        fileBean.setFileName("102.pdf");
        fileBean.setTaskUid(taskBean);

        Set<FileBean> fileBeans = new LinkedHashSet<FileBean>();
        fileBeans.add(fileBean);

        taskManagerService.save(taskBean);
    }

    @Test
    public void testGetTaskByInternalNumber() {
        assertNotNull(taskManagerService.getTaskByInternalNumber(101));
    }

    @Test
    public void testGetTaskByExternalNumber() {
        assertNotNull(taskManagerService.getTaskByExternalNumber("Исх-202"));
    }

    @Test
    public void testGetTaskByIncomingNumber() {
        assertNotNull(taskManagerService.getTaskByIncomingNumber(1));
    }

    @Test
    public void testGetTasksByExternalAssignee() {
        assertNotNull(taskManagerService.getTasksByExternalAssignee("Магафуров"));
    }

    @Test
    public void testGetTasksByDescription() {
        assertNotNull(taskManagerService.getTasksByDescription("Медсервис"));
    }

    @Test
    public void testGetNewInternalNumber() {
        assertEquals(407, taskManagerService.getNewInternalNumber());
    }

    @Test
    public void testGetNewIncomingNumber() {
        assertEquals(402, taskManagerService.getNewIncomingNumber());
    }

    @Test
    public void testGetfiles() {
        TaskBean taskBean = taskManagerService.get(2620L);
        assertNotNull(taskBean.getFilesSet());
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }
}
