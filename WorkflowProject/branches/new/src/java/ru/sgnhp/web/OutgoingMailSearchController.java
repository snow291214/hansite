package ru.sgnhp.web;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.OutgoingMailSearchDto;
import ru.sgnhp.service.IUserManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class OutgoingMailSearchController extends SimpleFormController {

    private IUserManagerService userManagerService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        List<WorkflowUserBean> users = userManagerService.getAll();
        request.setAttribute("users", users);
        OutgoingMailSearchDto outgoingMailSearchDto = new OutgoingMailSearchDto();
        return outgoingMailSearchDto;
    }

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException e) {
        OutgoingMailSearchDto searchTaskBean = (OutgoingMailSearchDto) command;
        String searchType = request.getParameter("searchType");
        String responsibleUid = request.getParameter("combobox");
        if (responsibleUid != null) {
            searchTaskBean.setResponsibleUid(Long.parseLong(responsibleUid));
        }
        searchTaskBean.setSearchType(Integer.parseInt(searchType));
        request.getSession().setAttribute("searchTaskBean", command);
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
