package ru.sgnhp.web;

import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.service.IDocumentService;
import ru.sgnhp.service.ITaskManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class AjaxController implements Controller {

    private ITaskManagerService taskManagerService;
    private IDocumentService documentService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String documentTypeUid = request.getParameter("documentTypeUid");
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        if (documentTypeUid != null) {
            response.setContentType("text/xml");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<Number>");
            response.getWriter().write("<value>" + getDocumentService().getNewDocumentNumber(
                    year, Long.parseLong(documentTypeUid)) + "</value>");
            response.getWriter().write("</Number>");
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
}
