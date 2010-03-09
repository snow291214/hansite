/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.sgnhp.service.impl;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.domain.OutgoingMailBean;
import ru.sgnhp.service.IMailService;
import ru.sgnhp.service.IOutgoingMailService;

/**
 *
 * @author 48han
 */
public class MailServiceImplTest extends AbstractTransactionalDataSourceSpringContextTests{

    private IMailService mailService;
    private IOutgoingMailService outgoingMailService;

    public MailServiceImplTest() {
    }

    @Test
    public void testSendmailOutgoing() {
        OutgoingMailBean outgoingMailBean = outgoingMailService.get(13L);
        mailService.sendmailOutgoing(outgoingMailBean);
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    public void setOutgoingMailService(IOutgoingMailService outgoingMailService) {
        this.outgoingMailService = outgoingMailService;
    }

}