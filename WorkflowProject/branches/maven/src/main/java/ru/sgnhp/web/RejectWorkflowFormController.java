package ru.sgnhp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.dto.RejectWorkflowDto;
import ru.sgnhp.service.IStateManagerService;
import ru.sgnhp.service.IWorkflowManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 * Created on: 16.02.2010
 *
 *****
 */
public class RejectWorkflowFormController extends SimpleFormController {

    private IWorkflowManagerService workflowManagerService;
    private IStateManagerService stateManagerService;

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        RejectWorkflowDto rejectWorkflowDto = (RejectWorkflowDto) command;
        WorkflowBean workflowBean = workflowManagerService.getWorkflowByUid(rejectWorkflowDto.getWorkflowUid());
        workflowBean.setState(stateManagerService.get(4L));
        workflowBean = workflowManagerService.save(workflowBean);
        
        WorkflowBean wf = new WorkflowBean();
        wf.setParentUid(workflowBean.getUid());
        wf.setTaskBean(workflowBean.getTaskBean());
        wf.setAssignee(workflowBean.getAssignee());
        wf.setReceiver(workflowBean.getReceiver());
        wf.setDescription(rejectWorkflowDto.getDescription());
        wf.setState(stateManagerService.get(0L));
        wf.setAssignDate(DateUtils.nowDate());
        workflowManagerService.assignTaskToUser(wf);

        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        String workflowUid = request.getParameter("workflowID");
        RejectWorkflowDto rejectWorkflowDto = new RejectWorkflowDto();
        rejectWorkflowDto.setWorkflowUid(Long.parseLong(workflowUid));
        return rejectWorkflowDto;
    }

    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    public void setStateManagerService(IStateManagerService stateManagerService) {
        this.stateManagerService = stateManagerService;
    }
}
