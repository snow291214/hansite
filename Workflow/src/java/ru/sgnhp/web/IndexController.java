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
import ru.sgnhp.domain.WorkflowUser;
import ru.sgnhp.service.IUserManagerService;

public class IndexController implements Controller {

    private IUserManagerService userManagerService;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WorkflowUser user = userManagerService.getUserByLogin(request.getRemoteUser());
        //WorkflowUser user = userManagerService.getUserByLogin("ASU\\48han");
        /*
         * Смысл такой: если в базе нет такого пользователя, его сохраняем в базу,
         * и тут же его оттуда достаем, сохраняя в переменную user. Там будет его
         * Uid и логин, чтобы потом в дальнейшем использовать.
         *
         */
        if (user == null) {
            user = new WorkflowUser();
            user.setLogin(request.getRemoteUser());
            userManagerService.registerNewUser(user);
            user = userManagerService.getUserByLogin(request.getRemoteUser());
        }
        request.getSession().setAttribute("initiator", user);
        return new ModelAndView("index");
    }

    /**
     * @param userManagerService the userManagerService to set
     */
    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
