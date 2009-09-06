/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IUserManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

public class IndexController implements Controller {

    private IUserManagerService userManagerService;
    private IWorkflowManagerService workflowManagerService;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = ((WorkflowUserBean) request.getSession().getAttribute("initiator")).getLogin();
        WorkflowUserBean user = userManagerService.getUserByLogin(login);
        //WorkflowUser user = userManagerService.getUserByLogin("ASU\\48han");
        /*
         * Смысл такой: если в базе нет такого пользователя, его сохраняем в базу,
         * и тут же его оттуда достаем, сохраняя в переменную user. Там будет его
         * Uid и логин, чтобы потом в дальнейшем использовать.
         *
         */
        if (user == null) {
            user = (WorkflowUserBean) request.getSession().getAttribute("initiator");
            userManagerService.registerNewUser(user);
            user = userManagerService.getUserByLogin(login);
        }

        List<WorkflowBean> wfs = user.getWorkflows();

        /*for (Workflow wf : wfs) {
            wf.setAssignee(userManagerService.getUserByUid(wf.getParentUserUid()));
            wf.setReceiver(userManagerService.getUserByUid(wf.getUserUid()));
        }*/

        request.getSession().setAttribute("initiator", user);
        return new ModelAndView("index");
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
