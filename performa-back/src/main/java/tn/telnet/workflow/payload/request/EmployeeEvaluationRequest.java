package tn.telnet.workflow.payload.request;

import tn.telnet.workflow.models.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

public class EmployeeEvaluationRequest {

    private Long id;
    private User appraiser;
    private Date date;
    private String status;
    private Boolean isDraftMode;
    private Boolean isSubmitted;

    public EmployeeEvaluationRequest() {
    }

    public EmployeeEvaluationRequest(Long id, User appraiser, Date date, String status, boolean isDraftMode, boolean isSubmitted) {
        this.id = id;
        this.appraiser = appraiser;
        this.date = date;
        this.status = status;
        this.isDraftMode = isDraftMode;
        this.isSubmitted = isSubmitted;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAppraiser() {
        return appraiser;
    }

    public void setAppraiser(User appraiser) {
        this.appraiser = appraiser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDraftMode() {
        return isDraftMode;
    }

    public void setDraftMode(Boolean draftMode) {
        isDraftMode = draftMode;
    }

    public Boolean getSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(Boolean submitted) {
        isSubmitted = submitted;
    }
}
