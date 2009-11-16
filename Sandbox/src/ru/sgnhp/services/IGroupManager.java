/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.sgnhp.services;

import java.util.List;
import ru.sgnhp.entity.Groups;

/**
 *
 * @author 48han
 */
public interface IGroupManager extends IUniversalManager{
    /**
     * {@inheritDoc}
     */
    List getRoles(Groups groups);

    /**
     * {@inheritDoc}
     */
    Groups getRole(String groupName);

    /**
     * {@inheritDoc}
     */
    Groups saveRole(Groups groups);

    /**
     * {@inheritDoc}
     */
    void removeRole(String groupName);
}
