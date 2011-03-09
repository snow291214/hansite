package ru.sgnhp.service.impl;


import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.service.IStateManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class StateManagerServiceImplTest extends AbstractTransactionalDataSourceSpringContextTests {

    private IStateManagerService stateManagerService;

    @Test
    public void testGetById(){
        assertNotNull(stateManagerService.get(3L));
    }

    public void setStateManagerService(IStateManagerService stateManagerService) {
        this.stateManagerService = stateManagerService;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }
}
