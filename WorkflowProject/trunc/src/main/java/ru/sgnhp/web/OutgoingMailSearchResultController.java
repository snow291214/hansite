package ru.sgnhp.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.sgnhp.domain.OutgoingMailBean;
import ru.sgnhp.dto.OutgoingMailSearchDto;
import ru.sgnhp.service.IOutgoingMailService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class OutgoingMailSearchResultController implements Controller {

    private IOutgoingMailService outgoingMailService;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "index";
        OutgoingMailSearchDto outgoingMailSearchDto = (OutgoingMailSearchDto) request.getSession().getAttribute("searchTaskBean");
        List<OutgoingMailBean> outgoingMailBeans = new ArrayList<OutgoingMailBean>();
        switch (outgoingMailSearchDto.getSearchType()) {
            case 0:
                result = "outgoingSearchResult";
                outgoingMailBeans = outgoingMailService.getByDescription(outgoingMailSearchDto.getDescription());
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 1:
                result = "outgoingSearchResult";
                outgoingMailBeans = outgoingMailService.getByOutgoingNumber(outgoingMailSearchDto.getOutgoingNumber());
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 2:
                result = "outgoingSearchResult";
                outgoingMailBeans = outgoingMailService.getByDocumentumNumber(outgoingMailSearchDto.getDocumentumNumber());
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 3:
                result = "outgoingSearchResult";
                outgoingMailBeans = outgoingMailService.getByReceiverName(outgoingMailSearchDto.getReceiverName());
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 4:
                result = "outgoingSearchResult";
                outgoingMailBeans = outgoingMailService.getByReceiverCompany(outgoingMailSearchDto.getReceiverCompany());
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 5:
                result = "outgoingSearchResult";
                outgoingMailBeans = outgoingMailService.getByResponsibleUid(outgoingMailSearchDto.getResponsibleUid());
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 6:
                result = "outgoingSearchResult";
                outgoingMailBeans = outgoingMailService.getByPeriodOfDate(
                        outgoingMailSearchDto.getOutgoingDate(),
                        outgoingMailSearchDto.getDueDate());
                request.getSession().setAttribute("searchTaskBean", null);
                break;
            case 7:
                result = "outgoingSearchResult";
                outgoingMailBeans = outgoingMailService.getByPrimaveraUid(outgoingMailSearchDto.getPrimaveraUid());
                request.getSession().setAttribute("searchTaskBean", null);
                break;
        }
        return new ModelAndView(result, "outgoingMailBeans", outgoingMailBeans);
    }

    public void setOutgoingMailService(IOutgoingMailService outgoingMailService) {
        this.outgoingMailService = outgoingMailService;
    }
}
