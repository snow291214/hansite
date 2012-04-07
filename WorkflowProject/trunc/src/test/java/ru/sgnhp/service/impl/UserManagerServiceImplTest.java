package ru.sgnhp.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.Translit;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IUserManagerService;

/**
 *
 * @author 48han
 */
public class UserManagerServiceImplTest extends AbstractTransactionalDataSourceSpringContextTests {

    private IUserManagerService userManagerService;

    public UserManagerServiceImplTest() {
    }

    @Test
    public void testGetUserByLogin() {
        assertNotNull(userManagerService.getUserByLogin("77han"));
    }

    @Test
    public void testGetUserByEmail() {
        assertNotNull(userManagerService.getUserByEmail("77han@salavatmed.ru"));
    }

    @Test
    public void testUsersFile() throws IOException {
        FileWriter fileWriter = new FileWriter("d:\\temp\\wfusers.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        Translit translit = new Translit();
        for (Iterator<WorkflowUserBean> it = userManagerService.getAllEmailNotify().iterator(); it.hasNext();) {
            WorkflowUserBean workflowUserBean = it.next();
            if (workflowUserBean.getDepartment().getUid() == 11) {
                String name = "\"" + workflowUserBean.getLastName().replace("'", "");
                if (workflowUserBean.getFirstName().length() > 1) {
                    name += workflowUserBean.getFirstName().substring(0, 1).toUpperCase() + ".";
                }
                if (workflowUserBean.getMiddleName().length() > 1) {
                    name += workflowUserBean.getMiddleName().substring(0, 1).toUpperCase() + ".\"";
                }
                printWriter.println(workflowUserBean.getLogin() + " "
                        + translit.toTranslit(name) + " "
                        + workflowUserBean.getEmail());
            }
        }
        printWriter.close();
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
