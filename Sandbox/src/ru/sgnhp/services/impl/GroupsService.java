package ru.sgnhp.services.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgnhp.dao.IGroupsDao;
import ru.sgnhp.entity.Groups;
import ru.sgnhp.services.IGroupsService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
@Transactional(readOnly = true)
public class GroupsService implements IGroupsService {

    private IGroupsDao dao;

    public void setDao(IGroupsDao dao) {
        this.dao = dao;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveEntity(Groups entity) {
        dao.save(entity);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateEntity(Groups entity) {
        dao.save(entity);
    }
}
