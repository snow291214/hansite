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
        WorkflowUserBean user = (WorkflowUserBean) request.getSession().getAttribute("initiator");
        String login = user.getLogin();

        //Cookie cookie =  new LongLivedCookie("sessionUid", request.getSession().getId());
        //response.addCookie(null);
        //WorkflowUser user = userManagerService.getUserByLogin("ASU\\48han");
        /*
         * Смысл такой: если в базе нет такого пользователя, его сохраняем в базу,
         * и тут же его оттуда достаем, сохраняя в переменную user. Там будет его
         * Uid и логин, чтобы потом в дальнейшем использовать.
         *
         */
        //Необходимо переписать
        user = userManagerService.getUserByLogin(login);
        if (user == null) {
            user = (WorkflowUserBean) request.getSession().getAttribute("initiator");
            user = userManagerService.save(user);
        }
        request.getSession().setAttribute("initiator", user);

        List<WorkflowBean> workflows = getWorkflowManagerService().getRecievedWorkflowsByUserUid(user.getUid());
        request.setAttribute("workflows", workflows);
        return new ModelAndView("index","count", workflows.size());
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    public IWorkflowManagerService getWorkflowManagerService() {
        return workflowManagerService;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
