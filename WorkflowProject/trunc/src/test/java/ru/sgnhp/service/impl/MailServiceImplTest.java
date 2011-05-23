/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.sgnhp.service.impl;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.domain.OutgoingMailBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.service.IMailService;
import ru.sgnhp.service.IOutgoingMailService;
import ru.sgnhp.service.IWorkflowManagerService;

/**
 *
 * @author 48han
 */
public class MailServiceImplTest extends AbstractTransactionalDataSourceSpringContextTests{

    private IMailService mailService;
    private IOutgoingMailService outgoingMailService;
    private IWorkflowManagerService workflowManagerService;

    public MailServiceImplTest() {
    }

//    @Test
//    public void testSendmailOutgoing() {
//        OutgoingMailBean outgoingMailBean = outgoingMailService.get(13L);
//        mailService.sendmailOutgoing(outgoingMailBean);
//    }

//    @Test
//    public void testSendmailAssign(){
//        WorkflowBean workflowBean = workflowManagerService.getWorkflowByUid(935L);
//        mailService.sendmailAssign(workflowBean);
//    }

    @Test
    public void testTaskReport(){
        //WorkflowBean workflowBean = workflowManagerService.getWorkflowByUid(3784L);
        //mailService.sendmailAssign(workflowBean);
        workflowManagerService.taskReport();
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

    public IWorkflowManagerService getWorkflowManagerService() {
        return workflowManagerService;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

}