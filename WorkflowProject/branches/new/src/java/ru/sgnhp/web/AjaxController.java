package ru.sgnhp.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.service.IDocumentService;
import ru.sgnhp.service.IDocumentTypeService;
import ru.sgnhp.service.ITaskManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class AjaxController implements Controller {

    private ITaskManagerService taskManagerService;
    private IDocumentService documentService;
    private IDocumentTypeService documentTypeService;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String documentTypeUid = request.getParameter("documentTypeUid");
        String currentYear = request.getParameter("currentYear");
        if (documentTypeUid != null) {
            if (documentTypeUid.equals("1")) {
                response.setContentType("text/xml");
                response.setCharacterEncoding("utf-8");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<Number>");
                response.getWriter().write("<value>" + documentService.getNewDocumentNumber(
                        Integer.parseInt(currentYear), Long.parseLong(documentTypeUid)) + "</value>");
                response.getWriter().write("</Number>");
            }
        } else {
            response.setContentType("text/xml");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<InternalNumber>");
            response.getWriter().write("<value>" + taskManagerService.getNewIncomingNumber() + "</value>");
            response.getWriter().write("</InternalNumber>");
        }
        return null;
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    public IDocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(IDocumentService documentService) {
        this.documentService = documentService;
    }

    public void setDocumentTypeService(IDocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }
}
