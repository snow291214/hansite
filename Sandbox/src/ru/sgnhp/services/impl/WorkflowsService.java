package ru.sgnhp.services.impl;

import ru.sgnhp.dao.IGenericDao;
import ru.sgnhp.entity.Workflows;
import ru.sgnhp.services.IWorkflowsService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class WorkflowsService extends GenericService<Workflows, Long> implements IWorkflowsService {

    public WorkflowsService(IGenericDao<Workflows, Long> genericDao) {
        super(genericDao);
    }
}
