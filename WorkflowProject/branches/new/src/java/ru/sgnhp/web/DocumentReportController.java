/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.web;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import ru.sgnhp.domain.TaskBean;
import ru.sgnhp.dto.DocumentReportDto;
import ru.sgnhp.service.IOutgoingMailService;
import ru.sgnhp.service.ITaskManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class DocumentReportController extends SimpleFormController {

    private ITaskManagerService taskManagerService;
    private IOutgoingMailService outgoingMailService;

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) throws ParseException {
        DocumentReportDto documentReportDto = (DocumentReportDto)command;
        documentReportDto.setReportType(Integer.parseInt(request.getParameter("reportType")));
        List model = null;
        String view = "documentReports";
        switch(documentReportDto.getReportType()){
            case 0:
                model = taskManagerService.getAllIncomingMailByYear(documentReportDto.getReportYear());
                view = "incomingMailReport";
                break;
        }

        return new ModelAndView(view, "model", model);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        DocumentReportDto documentReportDto = new DocumentReportDto();
        Calendar calendar = Calendar.getInstance();
        documentReportDto.setReportYear(calendar.get(Calendar.YEAR));
        return documentReportDto;
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
