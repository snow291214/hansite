/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.sgnhp.domain;

/**
 *
 * @author 48han
 */
public class StateBean {
    private Long stateUid;
    private String stateDescription;

    public Long getStateUid() {
        return stateUid;
    }

    public void setStateUid(Long stateUid) {
        this.stateUid = stateUid;
    }

    public String getStateDescription() {
        return stateDescription;
    }

    public void setStateDescription(String stateDescription) {
        this.stateDescription = stateDescription;
    }
}
