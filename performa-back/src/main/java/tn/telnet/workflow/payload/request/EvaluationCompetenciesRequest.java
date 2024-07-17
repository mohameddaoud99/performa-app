package tn.telnet.workflow.payload.request;


import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import tn.telnet.workflow.models.Competency;
import tn.telnet.workflow.models.EmployeeEvaluation;

public class EvaluationCompetenciesRequest {

    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    //@JsonIgnore
    private EmployeeEvaluation evaluation;
    @ManyToOne
    @JoinColumn(name = "competency_id")
    //@JsonIgnore
    private Competency competency;
    @Column(name = "employee_mark")
    private String employee_mark;

    @Column(name = "appraiser_mark")
    private String appraiser_mark;

    @Column(name = "employee_comment")
    private String employee_comment;

    @Column(name = "appraiser_comment")
    private String appraiser_comment;

    @Column(name = "points")
    private String points;


    public EmployeeEvaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(EmployeeEvaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Competency getCompetency() {
        return competency;
    }

    public void setCompetency(Competency competency) {
        this.competency = competency;
    }

    public String getEmployee_mark() {
        return employee_mark;
    }

    public void setEmployee_mark(String employee_mark) {
        this.employee_mark = employee_mark;
    }

    public String getAppraiser_mark() {
        return appraiser_mark;
    }

    public void setAppraiser_mark(String appraiser_mark) {
        this.appraiser_mark = appraiser_mark;
    }

    public String getEmployee_comment() {
        return employee_comment;
    }

    public void setEmployee_comment(String employee_comment) {
        this.employee_comment = employee_comment;
    }

    public String getAppraiser_comment() {
        return appraiser_comment;
    }

    public void setAppraiser_comment(String appraiser_comment) {
        this.appraiser_comment = appraiser_comment;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}