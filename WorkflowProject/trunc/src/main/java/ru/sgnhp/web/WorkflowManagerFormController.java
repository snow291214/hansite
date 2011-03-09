package ru.sgnhp.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowFileBean;
import ru.sgnhp.dto.WorkflowBeanDto;
import ru.sgnhp.service.IStateManagerService;
import ru.sgnhp.service.IWorkflowFileManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class WorkflowManagerFormController extends SimpleFormController {

    private IWorkflowManagerService workflowManagerService;
    private IStateManagerService stateManagerService;
    private IWorkflowFileManagerService workflowFileManagerService;

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) throws IOException {
        WorkflowBeanDto workflowBeanDto = (WorkflowBeanDto) command;
        Long stateUid = workflowBeanDto.getStateUid();
        if (stateUid == 3L) {
            workflowBeanDto.setWorkflowNote("Принято к сведению");
        }
        workflowBeanDto = workflowManagerService.updateWorkflowState(workflowBeanDto, stateManagerService.get(stateUid));

        WorkflowBean workflowBean = workflowManagerService.get(workflowBeanDto.getUid());
        /* Сохраняем файлы */
        final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        final Map files = multiRequest.getFileMap();
        for (Object file : files.values()) {
            String fileName = ((MultipartFile) file).getOriginalFilename();
            if (!fileName.equals("")) {
                WorkflowFileBean bean = new WorkflowFileBean();
                bean.setWorkflowBean(workflowBean);
                bean.setFileName(fileName);
                bean.setBlobField(((MultipartFile) file).getBytes());
                workflowFileManagerService.save(bean);
            }
        }
        Map<String, String> model = new HashMap<String, String>();
        model.put("pageNum", workflowBeanDto.getPageNumber());
        return new ModelAndView(new RedirectView(getSuccessView()), model);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        request.setAttribute("actionUrl", "workflowManager.htm");
        String workflowUid = request.getParameter("workflowID");
        String pageNumber = request.getParameter("pageNum");
        WorkflowBean workflowBean = getWorkflowManagerService().getWorkflowByUid(Long.parseLong(workflowUid));
        ArrayList<WorkflowBean> roadmap = new ArrayList<WorkflowBean>();
        if (workflowBean.getParentUid() == -1) {
            roadmap.add(workflowBean);
        } else {
            roadmap.add(workflowBean);
            roadmap = this.workflowManagerService.getWorkflowMembersByWorkflowUid(workflowBean.getUid(),
                    workflowBean.getParentUid(), roadmap);
            Collections.reverse(roadmap);
        }
        request.setAttribute("roadmap", roadmap);
        request.setAttribute("workflowID", workflowUid);
        request.setAttribute("pageNum", pageNumber);
        WorkflowBeanDto workflowBeanDto = new WorkflowBeanDto();
        workflowBeanDto.setUid(workflowBean.getUid());
        workflowBeanDto.setStateUid(workflowBean.getState().getStateUid());
        workflowBeanDto.setPageNumber(pageNumber);
        workflowBean = null;
        return workflowBeanDto;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    public IWorkflowManagerService getWorkflowManagerService() {
        return workflowManagerService;
    }

    public void setStateManagerService(IStateManagerService stateManagerService) {
        this.stateManagerService = stateManagerService;
    }

    public void setWorkflowFileManagerService(IWorkflowFileManagerService workflowFileManagerService) {
        this.workflowFileManagerService = workflowFileManagerService;
    }
}
