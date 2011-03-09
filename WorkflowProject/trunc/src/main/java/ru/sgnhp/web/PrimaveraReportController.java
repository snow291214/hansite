package ru.sgnhp.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.service.IOutgoingMailService;
import ru.sgnhp.service.ITaskManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class PrimaveraReportController implements Controller {

    private ITaskManagerService taskManagerService;
    private IOutgoingMailService outgoingMailService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String primaveraUid = request.getParameter("combobox");
        if (primaveraUid == null) {
            ArrayList<String> primaveraUids = new ArrayList<String>();
            List<String> taskBeans = taskManagerService.getDistinctPrimaveraIDS();
            primaveraUids.addAll(taskBeans);
            List<String> outgoingMails = outgoingMailService.getAllOutgoingMailWithPrimaveraUid();
            primaveraUids.addAll(outgoingMails);
            HashSet<String> hashSet = new HashSet(primaveraUids);
            primaveraUids = new ArrayList<String>(hashSet);
            Collections.sort(primaveraUids);
            return new ModelAndView("primaveraReportWizard", "primaveraUids", primaveraUids);
        } else {
            return new ModelAndView(new RedirectView("primaveraReport.htm"), "primaveraUid", primaveraUid);
        }
    }

    public ITaskManagerService getTaskManagerService() {
        return taskManagerService;
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    public IOutgoingMailService getOutgoingMailService() {
        return outgoingMailService;
    }

    public void setOutgoingMailService(IOutgoingMailService outgoingMailService) {
        this.outgoingMailService = outgoingMailService;
    }
}
