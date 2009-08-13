package ru.sgnhp.web;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import ru.sgnhp.domain.FileUploadBean;
import ru.sgnhp.domain.Task;
import ru.sgnhp.domain.Workflow;
import ru.sgnhp.domain.WorkflowUser;
import ru.sgnhp.service.ITaskManagerService;
import ru.sgnhp.service.IUploadManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class UploadController extends SimpleFormController {

    private static final String destinationDir = "C:/temp/";
    private IUploadManagerService uploadManagerService;
    private ITaskManagerService taskManagerService;
    private IWorkflowManagerService workflowManagerService;

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {

        /* Сохраняем задание */
        Task task = (Task) request.getSession().getAttribute("task");
        task = taskManagerService.saveTask(task);

        /* Назначаем задание пользователям */
        WorkflowUser initiator = (WorkflowUser) request.getSession().getAttribute("initiator");
        String[] userUids = (String[]) request.getSession().getAttribute("checks");
        for (String uid : userUids) {
            Workflow wf = new Workflow();
            wf.setTaskUid(task.getUid());
            wf.setParentUserUid(initiator.getUid());
            wf.setUserUid(Long.valueOf(uid));
            wf.setDescription(task.getDescription());
            wf.setState(Short.valueOf("0"));
            wf.setAssignDate(task.getStartDate());
            workflowManagerService.assignTaskToUser(wf);
        }
        /* Сохраняем файлы */
        final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        final Map files = multiRequest.getFileMap();
        for (Object file : files.values()) {
            FileUploadBean bean = new FileUploadBean();
            bean.setTaskUid(task.getUid());
            bean.setFileName(((MultipartFile) file).getOriginalFilename());
            bean.setContentStream(((MultipartFile) file).getInputStream());
            uploadManagerService.saveFile(bean);
        }
        request.getSession().setAttribute("task", null);
        request.getSession().setAttribute("checks", null);
        return new ModelAndView(getSuccessView(), getCommandName(), command);
    }

    public IUploadManagerService getUploadManagerService() {
        return uploadManagerService;
    }

    public void setUploadManagerService(IUploadManagerService uploadManagerService) {
        this.uploadManagerService = uploadManagerService;
    }

    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }
}
