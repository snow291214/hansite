package ru.sgnhp.web;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.Translit;
import ru.sgnhp.domain.WorkflowFileBean;
import ru.sgnhp.service.IWorkflowFileManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class DownloadWorkflowFilesController implements Controller {

    private IWorkflowFileManagerService workflowFileManagerService;
    static final private String CONTENT_TYPE_CHARSET = "charset=windows-1251";

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileUid = request.getParameter("fileID");

        WorkflowFileBean bean = workflowFileManagerService.get(Long.parseLong(fileUid));

        String fileType = bean.getFileName().substring(bean.getFileName().indexOf(".") + 1, bean.getFileName().length());
        if (fileType.trim().equalsIgnoreCase("txt")) {
            response.setContentType("text/plain" + "; " + CONTENT_TYPE_CHARSET);
        } else if (fileType.trim().equalsIgnoreCase("doc")) {
            response.setContentType("application/msword" + "; " + CONTENT_TYPE_CHARSET);
        } else if (fileType.trim().equalsIgnoreCase("xls")) {
            response.setContentType("application/vnd.ms-excel" + "; " + CONTENT_TYPE_CHARSET);
        } else if (fileType.trim().equalsIgnoreCase("pdf")) {
            response.setContentType("application/pdf" + "; " + CONTENT_TYPE_CHARSET);
        } else if (fileType.trim().equalsIgnoreCase("ppt")) {
            response.setContentType("application/ppt" + "; " + CONTENT_TYPE_CHARSET);
        } else {
            response.setContentType("application/octet-stream" + "; " + CONTENT_TYPE_CHARSET);
        }
        Translit translit = new Translit();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + translit.toTranslit(bean.getFileName()) + "\"");
        response.setHeader("cache-control", "must-revalidate");

        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bs = bean.getBlobField();
        outputStream.write(bs);
        outputStream.flush();
        outputStream.close();
        return null;
    }

    public IWorkflowFileManagerService getWorkflowFileManagerService() {
        return workflowFileManagerService;
    }

    public void setWorkflowFileManagerService(IWorkflowFileManagerService workflowFileManagerService) {
        this.workflowFileManagerService = workflowFileManagerService;
    }
}
