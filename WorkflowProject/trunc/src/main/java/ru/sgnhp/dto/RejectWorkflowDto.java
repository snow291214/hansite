package ru.sgnhp.dto;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 * Created on: 16.02.2010
 *
 *****
 */
public class RejectWorkflowDto {

    private Long workflowUid;
    private String description;

    public Long getWorkflowUid() {
        return workflowUid;
    }

    public void setWorkflowUid(Long workflowUid) {
        this.workflowUid = workflowUid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
