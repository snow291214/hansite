/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.sgnhp.service;

import ru.sgnhp.domain.StateBean;

/**
 *
 * @author 48han
 */
public interface IStateManagerService {
    StateBean getStateByStateUid(int stateUid);
}
