/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.sgnhp.web;

import org.springframework.web.servlet.mvc.SimpleFormController;
import ru.sgnhp.service.IWorkflowManagerService;

/**
 *
 * @author 48han
 */
public class SearchTaskFormController extends SimpleFormController{
    private IWorkflowManagerService workflowManagerService;

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
