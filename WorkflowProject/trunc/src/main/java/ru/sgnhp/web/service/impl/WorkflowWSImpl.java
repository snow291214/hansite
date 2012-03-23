package ru.sgnhp.web.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.sgnhp.DateUtils;
import ru.sgnhp.domain.*;
import ru.sgnhp.dto.DocumentDto;
import ru.sgnhp.dto.DocumentFileDto;
import ru.sgnhp.service.*;
import ru.sgnhp.web.service.IWorkflowWS;

/**
 *
 * Khudyakov Alexey Skype: khudyakov.alexey Email: khudyakov.alexey@gmail.com
 *
 */
public class WorkflowWSImpl implements IWorkflowWS {

    private IDocumentService documentService;
    private IDocumentTypeService documentTypeService;
    private IDocumentFileService documentFileService;
    private IUserManagerService userManagerService;
    private ITaskManagerService taskManagerService;
    private IWorkflowManagerService workflowManagerService;
    private IStateManagerService stateManagerService;
    private IUploadManagerService uploadManagerService;

    @Override
    public String echo(String text) {
        return text;
    }

    @Override
    public void saveDocument(DocumentDto documentDto) {

        /*
         * Сохранение документа в БД
         */
        DocumentBean documentBean = new DocumentBean();
        documentBean.setDescription(documentDto.getDescription());
        documentBean.setDocumentDate(documentDto.getDocumentDate());
        documentBean.setDocumentNumber(documentDto.getIncomingNumber());
        documentBean.setDocumentPrefix(documentDto.getDocumentPrefix().trim().toUpperCase());
        documentBean.setDocumentTypeBean(getDocumentTypeService().get(documentDto.getDocumentTypeUid()));
        documentBean.setContactPerson(userManagerService.get(documentDto.getContactUserUid()));
        documentBean.setControlPerson(userManagerService.get(documentDto.getControlUserUid()));
        documentBean = getDocumentService().save(documentBean);

        /*
         * Сохранение файлов в БД
         */
        Set<DocumentFileBean> documentFileBeans = new HashSet<DocumentFileBean>();
        for (DocumentFileDto file : documentDto.getDocuments()) {
            try {
                DocumentFileBean documentFileBean = new DocumentFileBean();
                documentFileBean.setDocumentBean(documentBean);
                documentFileBean.setFileName(file.getFileName());
                documentFileBean.setBlobField(file.getBlobField());
                documentFileBeans.add(getDocumentFileService().save(documentFileBean));
            } catch (IOException ex) {
                Logger.getLogger(WorkflowWSImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /*
         * Создание и сохранение задачи ознакомления с распорядительным
         * документом
         */
        TaskBean taskBean = new TaskBean();
        taskBean.setInternalNumber(taskManagerService.getNewInternalNumber());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        if (documentBean.getDocumentPrefix().isEmpty()) {
            taskBean.setDescription(documentBean.getDocumentTypeBean().getDescription()
                    + " № " + String.valueOf(documentBean.getDocumentNumber())
                    + " от " + df.format(documentBean.getDocumentDate())
                    + ". " + documentBean.getDescription());
        } else {
            taskBean.setDescription(documentBean.getDocumentTypeBean().getDescription()
                    + " № " + String.valueOf(documentBean.getDocumentNumber())
                    + "/" + documentBean.getDocumentPrefix()
                    + " от " + df.format(documentBean.getDocumentDate())
                    + ". " + documentBean.getDescription());
        }
        taskBean.setStartDate(DateUtils.nowDate());
        taskBean.setDueDate(DateUtils.nowDate());
        taskBean.setExternalAssignee(documentBean.getControlPerson().getLastName()
                + " " + documentBean.getControlPerson().getFirstName()
                + " " + documentBean.getControlPerson().getMiddleName());
        taskBean.setExternalCompany(documentDto.getExternalCompany());
        taskBean = getTaskManagerService().save(taskBean);

        /*
         * Создание списка файлов для задачи
         */
        Set<FileBean> fileBeans = new HashSet<FileBean>();
        for (Iterator<DocumentFileBean> it = documentFileBeans.iterator(); it.hasNext();) {
            try {
                DocumentFileBean documentFileBean = it.next();
                FileBean fileBean = new FileBean();
                fileBean.setTaskUid(taskBean);
                fileBean.setFileName(documentFileBean.getFileName());
                fileBean.setBlobField(documentFileBean.getBlobField());
                fileBeans.add(getUploadManagerService().save(fileBean));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(WorkflowWSImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WorkflowWSImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        taskBean.setFilesSet(fileBeans);

        /*
         * Рассылка пользователям задачи ознакомления с распорядительным
         * документом
         */
        WorkflowUserBean initiator = getUserManagerService().get(documentDto.getContactUserUid());
        String description = "Прошу ознакомиться с документом. ";
        for (String uid : documentDto.getUserUids()) {
            WorkflowBean wf = new WorkflowBean();
            wf.setParentUid(-1L);
            wf.setTaskBean(taskBean);
            wf.setAssignee(initiator);
            wf.setReceiver(getUserManagerService().get(Long.valueOf(uid)));
            wf.setDescription(description);
            wf.setState(getStateManagerService().get(0L));
            wf.setAssignDate(taskBean.getStartDate());
            getWorkflowManagerService().assignTaskToUser(wf);
        }
    }

    /**
     * @return the documentService
     */
    public IDocumentService getDocumentService() {
        return documentService;
    }

    /**
     * @param documentService the documentService to set
     */
    public void setDocumentService(IDocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * @return the documentTypeService
     */
    public IDocumentTypeService getDocumentTypeService() {
        return documentTypeService;
    }

    /**
     * @param documentTypeService the documentTypeService to set
     */
    public void setDocumentTypeService(IDocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    /**
     * @return the documentFileService
     */
    public IDocumentFileService getDocumentFileService() {
        return documentFileService;
    }

    /**
     * @param documentFileService the documentFileService to set
     */
    public void setDocumentFileService(IDocumentFileService documentFileService) {
        this.documentFileService = documentFileService;
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
     * @return the taskManagerService
     */
    public ITaskManagerService getTaskManagerService() {
        return taskManagerService;
    }

    /**
     * @param taskManagerService the taskManagerService to set
     */
    public void setTaskManagerService(ITaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    /**
     * @return the workflowManagerService
     */
    public IWorkflowManagerService getWorkflowManagerService() {
        return workflowManagerService;
    }

    /**
     * @param workflowManagerService the workflowManagerService to set
     */
    public void setWorkflowManagerService(IWorkflowManagerService workflowManagerService) {
        this.workflowManagerService = workflowManagerService;
    }

    /**
     * @return the stateManagerService
     */
    public IStateManagerService getStateManagerService() {
        return stateManagerService;
    }

    /**
     * @param stateManagerService the stateManagerService to set
     */
    public void setStateManagerService(IStateManagerService stateManagerService) {
        this.stateManagerService = stateManagerService;
    }

    /**
     * @return the uploadManagerService
     */
    public IUploadManagerService getUploadManagerService() {
        return uploadManagerService;
    }

    /**
     * @param uploadManagerService the uploadManagerService to set
     */
    public void setUploadManagerService(IUploadManagerService uploadManagerService) {
        this.uploadManagerService = uploadManagerService;
    }
}
