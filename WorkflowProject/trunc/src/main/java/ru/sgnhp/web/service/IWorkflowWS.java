package ru.sgnhp.web.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import ru.sgnhp.dto.DocumentDto;

/**
 * ***
 *
 * @author Alexey Khudyakov @Skype: khudyakov.alexey
 *
 *****
 */
@WebService(name = "WorkflowService", targetNamespace = "http://office.salavatmed.ru")
public interface IWorkflowWS {

    @WebMethod(operationName = "testEcho", action = "urn:echo")
    @WebResult(name = "echo")
    public String echo(@WebParam(name = "echoText") String text);
    
    @WebMethod(operationName = "saveDocument", action = "urn:saveDocument")
    @WebResult(name = "saveDocument")    
    public void saveDocument(@WebParam(name = "documentDto") DocumentDto documentDto);
}
