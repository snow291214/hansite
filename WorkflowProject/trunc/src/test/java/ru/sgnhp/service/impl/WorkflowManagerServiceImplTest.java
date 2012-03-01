package ru.sgnhp.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.*;

/**
 *
 * @author 48han
 */
public class WorkflowManagerServiceImplTest extends AbstractTransactionalDataSourceSpringContextTests {

    private ITaskManagerService taskManagerService;
    private IWorkflowManagerService workflowManagerService;
    private IStateManagerService stateManagerService;
    private IUserManagerService userManagerService;
    private IMailService mailService;
    private final Long parentUid = 2L;
    private final Long userUid = 2L;
    private final Long workflowUid = 31L;

    public WorkflowManagerServiceImplTest() {
    }

//    @Test
//    public void testSave() {
//        WorkflowBean wf = new WorkflowBean();
//        wf.setParentUid(-1L);
//        wf.setTaskBean(new TaskBean(520L));
//        wf.setAssignee(new WorkflowUserBean(78L));
//        wf.setReceiver(new WorkflowUserBean(72L));
//        wf.setState(new StateBean(3L));
//        wf.setDescription("TEEEEEEEEEEEEEEEEEEEEEEEST!!!");
//        wf = workflowManagerService.assignTaskToUser(wf);
//        logger.info("Before");
//        logger.info(wf.getUid());
//        logger.info(wf.getDescription());
//        logger.info(wf.getReceiver().getFirstName());
//        logger.info(wf.getReceiver().getEmail());
//        logger.info(wf.getAssignee().getFirstName());
//        logger.info(wf.getAssignee().getEmail());
//        logger.info(wf.getState().getStateDescription());
//        logger.info(wf.getTaskBean().getDescription());
//        logger.info("---------------------------------");
//
//        wf.setState(stateManagerService.get(2L));
//        wf.setTaskBean(taskManagerService.get(529L));
//        wf = workflowManagerService.save(wf);
//        logger.info("After");
//        logger.info(wf.getUid());
//        logger.info(wf.getDescription());
//        logger.info(wf.getReceiver().getFirstName());
//        logger.info(wf.getReceiver().getEmail());
//        logger.info(wf.getAssignee().getFirstName());
//        logger.info(wf.getAssignee().getEmail());
//        logger.info(wf.getState().getStateDescription());
//        logger.info(wf.getTaskBean().getDescription());
//        logger.info("---------------------------------");
//    }
//    @Test
//    public void testTaskReminder() {
//        List<WorkflowUserBean> users = getUserManagerService().getAllEmailNotify();
//        for (Iterator<WorkflowUserBean> it = users.iterator(); it.hasNext();) {
//            WorkflowUserBean user = it.next();
//            List<WorkflowBean> workflows = workflowManagerService.getRecievedWorkflowsByUserUid(user.getUid());
//            if (workflows != null) {
//                mailService.sendmailSheduler((ArrayList) workflows);
//            }
//        }
//    }

//    @Test
//    public void testTaskReport() {
//        //List<WorkflowUserBean> users = userManagerService.getAll();
//        List<WorkflowUserBean> users = new ArrayList<WorkflowUserBean>();
//        users.add(userManagerService.getUserByLogin("77llv"));
//        for (Iterator<WorkflowUserBean> it = users.iterator(); it.hasNext();) {
//            WorkflowUserBean user = it.next();
//            List<WorkflowBean> uncompletedWorkflows = workflowManagerService.getAllUncompletedByParentUserUid(user.getUid());
//            if (uncompletedWorkflows != null) {
//                mailService.sendmailReport(uncompletedWorkflows);
//            }
//        }
//    }

    @Test
    public void testTaskReportEx() {
        //List<WorkflowUserBean> users = userManagerService.getAll();
        List<WorkflowUserBean> users = new ArrayList<WorkflowUserBean>();
        users.add(userManagerService.getUserByLogin("77llv"));
        for (Iterator<WorkflowUserBean> it = users.iterator(); it.hasNext();) {
            WorkflowUserBean user = it.next();
            List<WorkflowBean> uncompletedWorkflows = workflowManagerService.getAllUncompletedByParentUserUidEx(user.getUid());
            if (uncompletedWorkflows != null) {
                mailService.sendmailReportForDirector(uncompletedWorkflows);
            }
        }
    }
    
    @Test
    public void testAssignTaskToUser() {
    }

    @Test
    public void testIsWorkflowActive(){
        assertEquals(false, workflowManagerService.isWorkflowActive(763L));
    }

//    @Test
//    public void testGetRecievedWorkflowsByUserUid() {
//        List<WorkflowBean> workflowBeans = workflowManagerService.getRecievedWorkflowsByUserUid(userUid);
//        assertNotNull(workflowBeans);
//        assertNotNull(workflowBeans);
//        assertEquals(4, workflowBeans.size());
//        assertEquals("Худяков", workflowBeans.get(0).getAssignee().getLastName());
//    }

    @Test
    public void testGetAssignedWorkflowsByUserUid() {
        List<WorkflowBean> workflowBeans = workflowManagerService.getAssignedWorkflowsByUserUid(userUid, Boolean.TRUE);
        assertNotNull(workflowBeans);
        assertNotNull(workflowManagerService.getAssignedWorkflowsByUserUid(userUid, Boolean.FALSE));
        assertEquals(66, workflowBeans.size());
        assertEquals("Худяков", workflowBeans.get(0).getAssignee().getLastName());
    }

    @Test
    public void testGetCompletedWorkflowsByUserUid() {
        List<WorkflowBean> workflowBeans = workflowManagerService.getCompletedWorkflowsByUserUid(userUid);
        assertNotNull(workflowBeans);
        assertEquals(107, workflowBeans.size());
        assertEquals("Худяков", workflowBeans.get(0).getReceiver().getLastName());
    }

    @Test
    public void testGetWorkflowsByTaskUid() {
        Long uid = workflowManagerService.getWorkflowsByTaskUid(24L).get(0).getUid();
        assertEquals(workflowUid, uid);
    }

    public void testGetWorkflowsByPeriodOfDate() {
        List<WorkflowBean> workflowBeans =
                workflowManagerService.getWorkflowsByPeriodOfDate(1L, 1L,
                DateUtils.increaseDate(DateUtils.nowDate(), -60), DateUtils.nowDate());
        assertNotNull(workflowBeans);
    }

//    @Test
//    public void testTaskReport() {
//        workflowManagerService.taskReport();
//    }

//    @Test
//    public void testTasksForReviewReport() {
//        workflowManagerService.tasksForReviewReport();
//    }
    
//    @Test
//    public void testWorkflowsByParentUserUid() {
//        List<WorkflowBean> workflowBeans = workflowManagerService.getAllUncompletedByParentUserUid(75L);
//        for (WorkflowBean workflowBean : workflowBeans) {
//            System.out.println(workflowBean.getAssignee().getLastName() +"->"+
//                    workflowBean.getReceiver().getLastName()+": "+ workflowBean.getDescription());
//        }
//    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    public void setStateManagerService(IStateManagerService stateManagerService) {
        this.stateManagerService = stateManagerService;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public IUserManagerService getUserManagerService() {
        return userManagerService;
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
