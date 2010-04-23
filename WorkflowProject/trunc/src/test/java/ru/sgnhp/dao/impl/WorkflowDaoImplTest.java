/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.dao.impl;

import java.util.List;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.DateUtils;
import ru.sgnhp.dao.IStateDao;
import ru.sgnhp.dao.ITaskDao;
import ru.sgnhp.dao.IWorkflowDao;
import ru.sgnhp.domain.StateBean;
import ru.sgnhp.domain.TaskBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.WorkflowBeanDto;

/**
 *
 * @author 48han
 */
public class WorkflowDaoImplTest extends AbstractTransactionalDataSourceSpringContextTests {

    private IWorkflowDao workflowDao;
    private IStateDao stateDao;
    private ITaskDao taskDao;
    private final Long parentUid = 16L;
    private final Long userUid = 72L;
    private final Long workflowUid = 37L;

    public WorkflowDaoImplTest() {
    }

    @Test
    public void testSave() {
        WorkflowBean wf = new WorkflowBean();
        wf.setParentUid(-1L);
        wf.setTaskBean(new TaskBean(520L));
        wf.setAssignee(new WorkflowUserBean(78L));
        wf.setReceiver(new WorkflowUserBean(72L));
        wf.setState(new StateBean(3L));
        wf.setDescription("TEEEEEEEEEEEEEEEEEEEEEEEST!!!");
        wf = workflowDao.save(wf);
        logger.info("Before");
        logger.info(wf.getUid());
        logger.info(wf.getDescription());
        logger.info(wf.getReceiver().getFirstName());
        logger.info(wf.getAssignee().getFirstName());
        logger.info(wf.getState().getStateDescription());
        logger.info(wf.getTaskBean().getDescription());
        logger.info("---------------------------------");
        
        wf.setState(stateDao.get(2L));
        wf.setTaskBean(taskDao.get(529L));
        wf = workflowDao.save(wf);
        logger.info("After");
        logger.info(wf.getUid());
        logger.info(wf.getDescription());
        logger.info(wf.getReceiver().getFirstName());
        logger.info(wf.getAssignee().getFirstName());
        logger.info(wf.getState().getStateDescription());
        logger.info(wf.getTaskBean().getDescription());
        logger.info("---------------------------------");
    }

    @Test
    public void testGetWorkflowByParentUid() {
        assertNotNull(workflowDao.getWorkflowByParentUid(parentUid));
        assertEquals(2, workflowDao.getWorkflowByParentUid(parentUid).size());
        assertNotNull(workflowDao.getWorkflowByParentUid(parentUid).get(0).getReceiver());
        assertNotNull(workflowDao.getWorkflowByParentUid(parentUid).get(0).getAssignee());
        assertNotNull(workflowDao.getWorkflowByParentUid(parentUid).get(0).getTaskBean());
//        logger.info(workflowDao.getWorkflowByParentUid(parentUid).get(0).getTaskBean().getDescription());
//        logger.info(workflowDao.getWorkflowByParentUid(parentUid).get(0).getState().getStateDescription());
    }

//    @Test
//    public void testGetRecievedWorkflowsByUserUid() {
//        List<WorkflowBean> workflowBeans = workflowDao.getRecievedWorkflowsByUserUid(userUid);
//        //assertNotNull(workflowBeans);
//        assertEquals(2, workflowBeans.size());
//        assertEquals("Худяков", workflowBeans.get(0).getAssignee().getLastName());
//    }

    @Test
    public void testGetRecievedWorkflowsCountByUserUid() {
    }

//    @Test
//    public void testGetAssignedWorkflowsByUserUid() {
//        List<WorkflowBean> workflowBeans = workflowDao.getAssignedWorkflowsByUserUid(userUid, Boolean.TRUE);
//        assertNotNull(workflowBeans);
//        assertEquals(null, workflowDao.getAssignedWorkflowsByUserUid(userUid, Boolean.FALSE));
//        assertEquals(6, workflowBeans.size());
//        assertEquals("Харрасов", workflowBeans.get(0).getAssignee().getLastName());
//    }

    @Test
    public void testGetAssignedWorkflowsCountByUserUid() {
    }

    @Test
    public void testGetCompletedWorkflowsByUserUid() {
        List<WorkflowBean> workflowBeans = workflowDao.getCompletedWorkflowsByUserUid(userUid);
        //assertNotNull(workflowBeans);
        assertEquals(48, workflowBeans.size());
        assertEquals("Худяков", workflowBeans.get(0).getReceiver().getLastName());
    }

    @Test
    public void testGetCompletedWorkflowsCountByUserUid() {
    }

    @Test
    public void testGetWorkflowsByTaskUid() {
        Long uid = workflowDao.getWorkflowsByTaskUid(24L).get(0).getUid();
        assertEquals(workflowUid, uid);
    }

    @Test
    public void testGetWorkflowsByDescription() {
        assertNotNull(workflowDao.getWorkflowsByDescription(userUid, "%Тест%"));
    }

    @Test
    public void testGetByAssignDate() {
        assertNotNull(workflowDao.getByAssignDate(DateUtils.stringToDate("26.11.2009", "dd.MM.yyyy")));
        assertEquals(11, workflowDao.getByAssignDate(DateUtils.stringToDate("26.11.2009", "dd.MM.yyyy")).size());
//        for (WorkflowBean wf : workflowDao.getByAssignDate(DateUtils.stringToDate("26.11.2009", "dd.MM.yyyy"))) {
//            logger.info(wf.getDescription());
//        }
    }

    @Test
    public void testUpdateWorkflowState(){
        StateBean stateBean = stateDao.get(1L);
        WorkflowBeanDto workflowBeanDto = new WorkflowBeanDto();
        workflowBeanDto.setUid(711L);
        workflowBeanDto.setDescription("Test!!");
        workflowDao.updateWorkflowState(workflowBeanDto, stateBean);
    }

    @Test
    public void testGetWorkflowsByUserUidAndStateUids(){
        Long[] stateUids = {0L,2L};
        assertNotNull(workflowDao.getWorkflowsByUserUidAndStateUids(75L, stateUids));
    }

    @Test
    public void testGetByFinishDate() {
    }

    @Test
    public void testGetRecievedWorkflows(){
        assertNotNull(workflowDao.getRecievedWorkflows());
    }

    @Test
    public void testGetByTaskUidAndUserUid(){
        Long taskUid = 1068L;
        assertEquals(false, this.workflowDao.isTaskAssignedToUser(taskUid, 101L));
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    public void setWorkflowDao(IWorkflowDao workflowDao) {
        this.workflowDao = workflowDao;
    }

    public void setStateDao(IStateDao stateDao) {
        this.stateDao = stateDao;
    }

    public void setTaskDao(ITaskDao taskDao) {
        this.taskDao = taskDao;
    }
}
