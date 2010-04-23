package ru.sgnhp.service.impl;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.service.IUserManagerService;

/**
 *
 * @author 48han
 */
public class UserManagerServiceImplTest extends AbstractTransactionalDataSourceSpringContextTests{

    private IUserManagerService userManagerService;

    public UserManagerServiceImplTest() {
    }

    @Test
    public void testGetUserByLogin() {
        assertNotNull(userManagerService.getUserByLogin("48han"));
    }

    @Test
    public void testGetUserByEmail() {
        assertNotNull(userManagerService.getUserByEmail("87lvs@snos.ru"));
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
}