package tn.telnet.workflow.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
public class EmployeeEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    private User user;


    private Long id_appraiser;

    @Nullable
    private Double final_mark;

    private String status;

    private String comments;

    private String objectives;

    private String rejection_reasons;

    @Nullable
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateAppraiserSignature;

    @Nullable
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateEmployeeSignature;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    //@JsonFormat(pattern = "yyyy-MM-dd")

    private Timestamp dateReviewerEvaluation;

    @Nullable
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateRHValidation;

    @Nullable
    private boolean isReviwerStepDraftMode;
    private boolean isReviwerStepeSubmitted;

    private boolean isDraftMode;
    private boolean isSubmitted;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
    @OneToMany(mappedBy = "evaluation")
    //@JsonIgnore
    private List<EvaluationCompetencies> evaluationCompetencies;


    public EmployeeEvaluation() {
    }

    public EmployeeEvaluation(Long id, User user, Long id_appraiser, Double final_mark, String status, String comments, String objectives, String rejection_reasons, @Nullable Date dateAppraiserSignature, @Nullable Date dateEmployeeSignature, Timestamp dateReviewerEvaluation, @Nullable Date dateRHValidation, boolean isReviwerStepDraftMode, boolean isReviwerStepeSubmitted, boolean isDraftMode, boolean isSubmitted, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.id_appraiser = id_appraiser;
        this.final_mark = final_mark;
        this.status = status;
        this.comments = comments;
        this.objectives = objectives;
        this.rejection_reasons = rejection_reasons;
        this.dateAppraiserSignature = dateAppraiserSignature;
        this.dateEmployeeSignature = dateEmployeeSignature;
        this.dateReviewerEvaluation = dateReviewerEvaluation;
        this.dateRHValidation = dateRHValidation;
        this.isReviwerStepDraftMode = isReviwerStepDraftMode;
        this.isReviwerStepeSubmitted = isReviwerStepeSubmitted;
        this.isDraftMode = isDraftMode;
        this.isSubmitted = isSubmitted;
        this.createdAt = createdAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId_appraiser() {
        return id_appraiser;
    }

    public void setId_appraiser(Long id_appraiser) {
        this.id_appraiser = id_appraiser;
    }

    public Double getFinal_mark() {
        return final_mark;
    }

    public void setFinal_mark(Double final_mark) {
        this.final_mark = final_mark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Nullable
    public Date getDateAppraiserSignature() {
        return dateAppraiserSignature;
    }

    public void setDateAppraiserSignature(@Nullable Date dateAppraiserSignature) {
        this.dateAppraiserSignature = dateAppraiserSignature;
    }

    @Nullable
    public Date getDateEmployeeSignature() {
        return dateEmployeeSignature;
    }

    public void setDateEmployeeSignature(@Nullable Date dateEmployeeSignature) {
        this.dateEmployeeSignature = dateEmployeeSignature;
    }

    public Timestamp getDateReviewerEvaluation() {
        return dateReviewerEvaluation;
    }

    public void setDateReviewerEvaluation(Timestamp dateReviewerEvaluation) {
        this.dateReviewerEvaluation = dateReviewerEvaluation;
    }

    @Nullable
    public Date getDateRHValidation() {
        return dateRHValidation;
    }

    public void setDateRHValidation(@Nullable Date dateRHValidation) {
        this.dateRHValidation = dateRHValidation;
    }

    public boolean isReviwerStepDraftMode() {
        return isReviwerStepDraftMode;
    }

    public void setReviwerStepDraftMode(boolean reviwerStepDraftMode) {
        isReviwerStepDraftMode = reviwerStepDraftMode;
    }

    public boolean isReviwerStepeSubmitted() {
        return isReviwerStepeSubmitted;
    }

    public void setReviwerStepeSubmitted(boolean reviwerStepeSubmitted) {
        isReviwerStepeSubmitted = reviwerStepeSubmitted;
    }

    public boolean isDraftMode() {
        return isDraftMode;
    }

    public void setDraftMode(boolean draftMode) {
        isDraftMode = draftMode;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<EvaluationCompetencies> getEvaluationCompetencies() {
        return evaluationCompetencies;
    }

    public void setEvaluationCompetencies(List<EvaluationCompetencies> evaluationCompetencies) {
        this.evaluationCompetencies = evaluationCompetencies;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getRejection_reasons() {
        return rejection_reasons;
    }

    public void setRejection_reasons(String rejection_reasons) {
        this.rejection_reasons = rejection_reasons;
    }

    @Override
    public String toString() {
        return "EmployeeEvaluation{" +
                "id_appraiser=" + id_appraiser +
                ", final_mark=" + final_mark +
                ", status='" + status + '\'' +
                ", comments='" + comments + '\'' +
                ", objectives='" + objectives + '\'' +
                ", rejection_reasons='" + rejection_reasons + '\'' +
                ", dateAppraiserSignature=" + dateAppraiserSignature +
                ", dateEmployeeSignature=" + dateEmployeeSignature +
                ", dateReviewerEvaluation=" + dateReviewerEvaluation +
                ", dateRHValidation=" + dateRHValidation +
                '}';
    }
}
