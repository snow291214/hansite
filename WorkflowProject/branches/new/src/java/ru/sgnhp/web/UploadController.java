package ru.sgnhp.web;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.domain.FileUploadBean;
import ru.sgnhp.domain.TaskBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.ITaskManagerService;
import ru.sgnhp.service.IUploadManagerService;
import ru.sgnhp.service.IUserManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class UploadController extends SimpleFormController {

    private IUploadManagerService uploadManagerService;
    private ITaskManagerService taskManagerService;
    private IWorkflowManagerService workflowManagerService;
    private IUserManagerService userManagerService;

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {

        /* Сохраняем задание */
        TaskBean task = (TaskBean) request.getSession().getAttribute("task");
        task = taskManagerService.saveTask(task);

        /* Назначаем задание пользователям */
        WorkflowUserBean initiator = (WorkflowUserBean) request.getSession().getAttribute("initiator");
        String[] userUids = (String[]) request.getSession().getAttribute("checks");
        for (String uid : userUids) {
            WorkflowBean wf = new WorkflowBean();
            wf.setParentUid(Long.parseLong("-1"));
            wf.setTaskUid(task.getUid());
            wf.setParentUserUid(initiator.getUid());
            wf.setUserUid(Long.valueOf(uid));
            wf.setDescription(task.getDescription());
            wf.setState("0");
            wf.setAssignDate(task.getStartDate());
            wf.setTask(task);
            wf.setAssignee(userManagerService.getUserByUid(wf.getParentUserUid()));
            wf.setReceiver(userManagerService.getUserByUid(wf.getUserUid()));
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
        return new ModelAndView(new RedirectView(getSuccessView()), getCommandName(), command);
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

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
