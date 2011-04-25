package ru.sgnhp.web;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.domain.FileBean;
import ru.sgnhp.domain.TaskBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.service.IStateManagerService;
import ru.sgnhp.service.ITaskManagerService;
import ru.sgnhp.service.IUploadManagerService;
import ru.sgnhp.service.IUserManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class UploadController extends SimpleFormController {

    private IUploadManagerService uploadManagerService;
    private ITaskManagerService taskManagerService;
    private IWorkflowManagerService workflowManagerService;
    private IUserManagerService userManagerService;
    private IStateManagerService stateManagerService;

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {

        /* Исправление непонятной и премерзкой ошибки */
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        WorkflowUserBean initiator = userManagerService.getUserByLogin(currentUser);

        /* Сохраняем задание */
        TaskBean task = (TaskBean) request.getSession().getAttribute("task");
        task = taskManagerService.save(task);

        /* Сохраняем файлы */
        final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        final Map files = multiRequest.getFileMap();
        Set<FileBean> f = new HashSet<FileBean>();
        for (Object file : files.values()) {
            String fileName = ((MultipartFile) file).getOriginalFilename();
            if (!fileName.equals("")) {
                FileBean bean = new FileBean();
                bean.setTaskUid(task);
                bean.setFileName(((MultipartFile) file).getOriginalFilename());
                bean.setBlobField(((MultipartFile) file).getBytes());
                f.add(uploadManagerService.save(bean));
            }
        }

        task.setFilesSet(f);

        /* Назначаем задание пользователям */
        //WorkflowUserBean initiator = (WorkflowUserBean) request.getSession().getAttribute("initiator");
        String[] userUids = (String[]) request.getSession().getAttribute("checks");
        for (String uid : userUids) {
            WorkflowBean wf = new WorkflowBean();
            wf.setParentUid(-1L);
            wf.setTaskBean(task);
            wf.setAssignee(initiator);
            wf.setReceiver(userManagerService.get(Long.valueOf(uid)));
            wf.setDescription(task.getDescription());
            wf.setState(stateManagerService.get(0L));
            wf.setAssignDate(task.getStartDate());
            wf = workflowManagerService.assignTaskToUser(wf);
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

    public void setStateManagerService(IStateManagerService stateManagerService) {
        this.stateManagerService = stateManagerService;
    }
}
