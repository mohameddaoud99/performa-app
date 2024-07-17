package tn.telnet.workflow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "evaluation_competencies")
public class EvaluationCompetencies {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    @JsonIgnore
    private EmployeeEvaluation evaluation;
     @ManyToOne
     @JoinColumn(name = "competency_id")
    //@JsonIgnore
    private Competency competency;
    @Column(name = "employee_mark")
    private Double employee_mark;

    @Column(name = "appraiser_mark")
    private Double appraiser_mark;

    @Column(name = "employee_comment")
    private String employee_comment;

    @Column(name = "appraiser_comment")
    private String appraiser_comment;

    @Column(name = "points")
    private Double points;

    @Column(name = "is_Selected")
    private Boolean isSelected;

    @Column(name = "weightage")
    private Double weightage;

    @Column(name = "level")
    private String level;

    public EvaluationCompetencies() {}

    public EvaluationCompetencies(Long id, EmployeeEvaluation evaluation, Competency competency, Double employee_mark, Double appraiser_mark, String employee_comment, String appraiser_comment, Double points, Boolean isSelected, Double weightage, String level) {
        this.id = id;
        this.evaluation = evaluation;
        this.competency = competency;
        this.employee_mark = employee_mark;
        this.appraiser_mark = appraiser_mark;
        this.employee_comment = employee_comment;
        this.appraiser_comment = appraiser_comment;
        this.points = points;
        this.isSelected = isSelected;
        this.weightage = weightage;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Double getEmployee_mark() {
        return employee_mark;
    }

    public void setEmployee_mark(Double employee_mark) {
        this.employee_mark = employee_mark;
    }

    public Double getAppraiser_mark() {
        return appraiser_mark;
    }

    public void setAppraiser_mark(Double appraiser_mark) {
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


    public Double getWeightage() {
        return weightage;
    }

    public void setWeightage(Double weightage) {
        this.weightage = weightage;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }
    public Boolean getSelected() {
        return isSelected;
    }
    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

}
