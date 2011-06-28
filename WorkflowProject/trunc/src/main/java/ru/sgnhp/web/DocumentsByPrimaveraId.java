package ru.sgnhp.web;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.service.IOutgoingMailService;
import ru.sgnhp.service.ITaskManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 * Created on: 03.03.2010
 *
 *****
 */
public class DocumentsByPrimaveraId implements Controller {

    private ITaskManagerService taskManagerService;
    private IOutgoingMailService outgoingMailService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String primaveraUid = request.getParameter("primaveraUid");
        if (primaveraUid == null) {
            return new ModelAndView("index");
        }
        ArrayList<Object> arrayList = new ArrayList<Object>();
        arrayList.add(taskManagerService.getTaskByPrimaveraUid(primaveraUid));
        arrayList.add(outgoingMailService.getByPrimaveraUid(primaveraUid));
        arrayList.add(primaveraUid);
        return new ModelAndView("primaveraReport", "arrayList", arrayList);
    }

    public void setOutgoingMailService(IOutgoingMailService outgoingMailService) {
        this.outgoingMailService = outgoingMailService;
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }
}
