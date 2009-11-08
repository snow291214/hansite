package ru.sgnhp.services;

import ru.sgnhp.entity.Groups;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public interface IGroupsService {

    public void saveEntity(Groups entity);

    public void updateEntity(Groups entity);
}
