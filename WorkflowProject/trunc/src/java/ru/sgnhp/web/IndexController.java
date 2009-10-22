/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IUserManagerService;

public class IndexController implements Controller {

    private IUserManagerService userManagerService;

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
        user = userManagerService.getUserByLogin(login);
        if (user == null) {
            user = (WorkflowUserBean) request.getSession().getAttribute("initiator");
            userManagerService.registerNewUser(user);
            user = userManagerService.getUserByLogin(login);
        }
        request.getSession().setAttribute("initiator", user);
        return new ModelAndView("index","count",user.getWorkflows().size());
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
