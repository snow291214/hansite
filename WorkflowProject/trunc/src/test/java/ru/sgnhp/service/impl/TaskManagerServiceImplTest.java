/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
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
    public void saveTask() throws FileNotFoundException, IOException {
        TaskBean taskBean = new TaskBean();
        taskBean.setInternalNumber(101);
        taskBean.setIncomingNumber(102);
        taskBean.setDescription("Test task");
        taskBean.setStartDate(DateUtils.nowDate());
        taskBean.setDueDate(DateUtils.increaseDate(DateUtils.nowDate(), 3));
        taskBean.setExternalAssignee("EA");
        taskBean.setExternalCompany("EC");
        taskBean = taskManagerService.save(taskBean);

        File file = new File("c:\\temp\\log4j.properties");
        InputStream is = new FileInputStream(file);
        // Get the size of the file
        long length = file.length();
        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        // Close the input stream and return bytes
        is.close();


        FileBean fileBean = new FileBean();
        fileBean.setBlobField(bytes);
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
        assertNotNull(taskManagerService.getTaskByExternalNumber("ИСХ-061-02-35134"));
    }

//    @Test
//    public void testGetTaskByIncomingNumber() {
//        assertNotNull(taskManagerService.getTaskByIncomingNumber(619));
//    }

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
        assertEquals(1368, taskManagerService.getNewInternalNumber());
    }

    @Test
    public void testGetNewIncomingNumber() {
        assertEquals(445, taskManagerService.getNewIncomingNumber());
    }

    @Test
    public void testGetByExternalCompany() {
        assertNotNull(taskManagerService.getTaskByExternalCompany("Кедр"));
    }

    @Test
    public void testGetfiles() {
        TaskBean taskBean = taskManagerService.get(12L);
        assertNotNull(taskBean.getFilesSet());
    }

    @Test
    public void testGetByPrimaveraUid() {
        assertNotNull(taskManagerService.getTaskByPrimaveraUid("32"));
    }

//    @Test
//    public void testDailyReport() {
//        taskManagerService.dailyReport();
//    }
    @Test
    public void testGetAllIncomingMail() throws ParseException {
        assertNotNull(taskManagerService.getAllIncomingMailByYear(2010));
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }
}
