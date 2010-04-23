/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sgnhp.web;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import ru.sgnhp.dto.DocumentReportDto;
import ru.sgnhp.service.IDocumentService;
import ru.sgnhp.service.IDocumentTypeService;
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
    private IDocumentTypeService documentTypeService;
    private IDocumentService documentService;

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) throws ParseException {
        DocumentReportDto documentReportDto = (DocumentReportDto) command;
        documentReportDto.setReportType(Integer.parseInt(request.getParameter("reportType")));
        List model = null;
        String view = "documentReports";
        ModelAndView mv = null;
        HashMap map = null;
        switch (documentReportDto.getReportType()) {
            case 0:
                view = "incomingMailReport";
                model = taskManagerService.getAllIncomingMailByYear(documentReportDto.getReportYear());
                break;
            case 1:
                view = "outgoingMailReport";
                model = outgoingMailService.getAllOutgoingMailByYear(documentReportDto.getReportYear());
                break;
            case 2:
                view = "orderReport";
                map = new HashMap();
                map.put("year", documentReportDto.getReportYear());
                map.put("documentType", "Приказов");
                map.put("documentTypeHeader", "приказа");
                map.put("orders", documentService.getAllDocumentsByYear(documentReportDto.getReportYear(),
                        documentTypeService.get(1L)));
                mv = new ModelAndView(view, "model", map);
                break;
            case 3:
                view = "orderReport";
                map = new HashMap();
                map.put("year", documentReportDto.getReportYear());
                map.put("documentType", "Распоряжений");
                map.put("documentTypeHeader", "распоряжения");
                map.put("orders", documentService.getAllDocumentsByYear(documentReportDto.getReportYear(),
                        documentTypeService.get(2L)));
                mv = new ModelAndView(view, "model", map);
                break;
        }
        if (mv != null) {
            return mv;
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

    public IDocumentTypeService getDocumentTypeService() {
        return documentTypeService;
    }

    public void setDocumentTypeService(IDocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    public IDocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(IDocumentService documentService) {
        this.documentService = documentService;
    }
}
