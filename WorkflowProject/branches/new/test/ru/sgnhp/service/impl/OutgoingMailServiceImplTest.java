package ru.sgnhp.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.OutgoingMailBean;
import ru.sgnhp.service.IOutgoingMailService;
import static org.junit.Assert.*;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */

public class OutgoingMailServiceImplTest extends AbstractTransactionalDataSourceSpringContextTests {

    private IOutgoingMailService outgoingMailService;

    public OutgoingMailServiceImplTest() {
    }

    @Test
    public void testGetByOutgoingNumber() {
        assertNotNull(outgoingMailService.getByOutgoingNumber(1L));
    }

    @Test
    public void testGetByOutgoingDate() throws ParseException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date = df.parse("11.01.2009");
        assertNotNull(outgoingMailService.getByOutgoingDate(date));
    }

    @Test
    public void testGetByDescription() {
        List<OutgoingMailBean> outgoingMailBeans = outgoingMailService.getByDescription("Тест");
        assertNotNull(outgoingMailBeans.get(0));
        assertNotNull(outgoingMailBeans.get(0).getWorkflowUserBean());
    }

    @Test
    public void testGetByDocumentumNumber() {
        assertNotNull(outgoingMailService.getByDocumentumNumber("Вх-07-223"));
    }

    @Test
    public void testGetByReceiverCompany() {
        assertNotNull(outgoingMailService.getByReceiverCompany("ПИи"));
    }

    @Test
    public void testGetByReceiverName() {
        assertNotNull(outgoingMailService.getByReceiverName("Кулешов"));
    }

    @Test
    public void testGetByResponsibleName() {
        assertNotNull(outgoingMailService.getByResponsibleUid(75L));
    }

    @Test
    public void testGetByDueDate() throws ParseException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date = df.parse("19.10.2009");
        assertNotNull(outgoingMailService.getByDueDate(date));
    }

    @Test
    public void testGetByPeriodOfDate() throws ParseException {
        Date outgoingDate = DateUtils.increaseDate(DateUtils.nowDate(), -30);
        Date dueDate = DateUtils.nowDate();
        assertNotNull(outgoingMailService.getByPeriodOfDate(outgoingDate, dueDate));
    }

    @Test
    public void testGetNewOutgoingNumber(){
        Long number = outgoingMailService.getNewOutgoingNumber();
        assertEquals(number, (Long)77L);
    }

    @Test
    public void testGetAllIncomingMailByYear() throws ParseException{
        assertNotNull(outgoingMailService.getAllOutgoingMailByYear(2010));
    }

    @Test
    public void testDailyReport() throws ParseException{
        outgoingMailService.dailyReport();
    }

    @Test
    public void testGetByPrimaveraUid(){
        assertNotNull(outgoingMailService.getByPrimaveraUid("W-AAA000TEST"));
    }

    public void setOutgoingMailService(IOutgoingMailService outgoingMailService) {
        this.outgoingMailService = outgoingMailService;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }
}
