package ru.sgnhp.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.OutgoingMailSearchDto;
import ru.sgnhp.service.IUserManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class OutgoingMailSearchController extends SimpleFormController {

    private IUserManagerService userManagerService;

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
        super.initBinder(request,binder);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        List<WorkflowUserBean> users = userManagerService.getAll();
        request.setAttribute("users", users);
        OutgoingMailSearchDto outgoingMailSearchDto = new OutgoingMailSearchDto();
        outgoingMailSearchDto.setOutgoingDate(DateUtils.increaseDate(DateUtils.nowDate(), -30));
        outgoingMailSearchDto.setDueDate(DateUtils.nowDate());
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
