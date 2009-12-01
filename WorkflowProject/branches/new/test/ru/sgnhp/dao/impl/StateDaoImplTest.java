package ru.sgnhp.dao.impl;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.dao.IStateDao;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class StateDaoImplTest extends AbstractTransactionalDataSourceSpringContextTests {

    public StateDaoImplTest() {
    }
    private IStateDao stateDao;

    @Test
    public void testGetState() {
        assertNotNull(stateDao.get(3L));
        assertNotNull(stateDao.get(3L).getWorkflowsSet());
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    public void setStateDao(IStateDao stateDao) {
        this.stateDao = stateDao;
    }
}
