package ru.sgnhp.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.service.IOutgoingMailService;
import static org.junit.Assert.*;

/**
 *
 * @author 48han
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
        assertNotNull(outgoingMailService.getByResponsibleName("Худяков"));
    }

    @Test
    public void testGetByDueDate() throws ParseException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date = df.parse("19.10.2009");
        assertNotNull(outgoingMailService.getByDueDate(date));
    }

    public void setOutgoingMailService(IOutgoingMailService outgoingMailService) {
        this.outgoingMailService = outgoingMailService;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }
}
