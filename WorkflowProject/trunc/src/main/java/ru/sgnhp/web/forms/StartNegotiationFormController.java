package ru.sgnhp.web.forms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.NegotiationBean;
import ru.sgnhp.domain.NegotiationFileBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.NegotiationDto;
import ru.sgnhp.service.INegotiationFileService;
import ru.sgnhp.service.INegotiationService;
import ru.sgnhp.service.INegotiationTypeService;
import ru.sgnhp.service.IUserManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class StartNegotiationFormController extends AbstractWizardFormController {

    private IUserManagerService userManagerService;
    private INegotiationService negotiationService;
    private INegotiationTypeService negotiationTypeService;
    private INegotiationFileService negotiationFileService;
    private int currentPage;

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
        super.initBinder(request, binder);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        WorkflowUserBean initiator = userManagerService.getUserByLogin(currentUser);
        request.setAttribute("negotiationTypes", negotiationTypeService.getAll());
        NegotiationDto negotiationDto = new NegotiationDto();
        negotiationDto.setNegotiationTypeUid("1");
        negotiationDto.setStartDate(DateUtils.nowDate());
        negotiationDto.setDueDate(DateUtils.increaseDate(DateUtils.nowDate(), 5));
        negotiationDto.setWorkflowUserBean(initiator);
        return negotiationDto;
    }

    @Override
    protected int getCurrentPage(HttpServletRequest request) {
        currentPage = super.getCurrentPage(request) + 1;
        return super.getCurrentPage(request);
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        switch (currentPage) {
            case 1:
                request.setAttribute("users", getUserManagerService().getAll());
                break;
        }
        if (request.getParameter("checks") != null) {
            request.getSession().setAttribute("checks", request.getParameterValues("checks"));
        }
        return super.handleRequest(request, response);
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException be) throws Exception {
        NegotiationDto negotiationDto = (NegotiationDto)command;
        negotiationDto.setNegotiatorUids((String[]) request.getSession().getAttribute("checks"));
        /*Создаем процесс*/
        NegotiationBean negotiationBean = negotiationService.createNewNegotiationProcess(negotiationDto, null);
        
        /* Сохраняем файлы */
        final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        final Map files = multiRequest.getFileMap();
        Set<NegotiationFileBean> f = new HashSet<NegotiationFileBean>();
        for (Object file : files.values()) {
            String fileName = ((MultipartFile) file).getOriginalFilename();
            if (!fileName.equals("")) {
                NegotiationFileBean bean = new NegotiationFileBean();
                bean.setNegotiationBean(negotiationBean);
                bean.setFileName(((MultipartFile) file).getOriginalFilename());
                bean.setBlobField(((MultipartFile) file).getBytes());
                f.add(negotiationFileService.save(bean));
            }
        }
        
        return new ModelAndView(new RedirectView("index.htm"));
    }

    /**
     * @return the userManagerService
     */
    public IUserManagerService getUserManagerService() {
        return userManagerService;
    }

    /**
     * @param userManagerService the userManagerService to set
     */
    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    /**
     * @return the negotiationTypeService
     */
    public INegotiationTypeService getNegotiationTypeService() {
        return negotiationTypeService;
    }

    /**
     * @param negotiationTypeService the negotiationTypeService to set
     */
    public void setNegotiationTypeService(INegotiationTypeService negotiationTypeService) {
        this.negotiationTypeService = negotiationTypeService;
    }

    /**
     * @return the negotiationFileService
     */
    public INegotiationFileService getNegotiationFileService() {
        return negotiationFileService;
    }

    /**
     * @param negotiationFileService the negotiationFileService to set
     */
    public void setNegotiationFileService(INegotiationFileService negotiationFileService) {
        this.negotiationFileService = negotiationFileService;
    }

    /**
     * @return the negotiationService
     */
    public INegotiationService getNegotiationService() {
        return negotiationService;
    }

    /**
     * @param negotiationService the negotiationService to set
     */
    public void setNegotiationService(INegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }
}
