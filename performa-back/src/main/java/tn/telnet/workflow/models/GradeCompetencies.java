package tn.telnet.workflow.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;


@Entity
    @Table(name = "grade_competencies")
    public class GradeCompetencies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @ManyToOne
    @JoinColumn(name = "competency_id")
    //@JsonIgnore
    private Competency competency;

    @Column(name = "weightage")
    private Double weightage;

    @Column(name = "level")
    private String level;

    @Column(name = "points")

    @DefaultValue("0.000")
    private double points;


    public GradeCompetencies() {}

    public GradeCompetencies(Grade grade, Competency competency, Double weightage, String level, double points) {
        this.grade = grade;
        this.competency = competency;
        this.weightage = weightage;
        this.level = level;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Competency getCompetency() {
        return competency;
    }

    public void setCompetency(Competency competency) {
        this.competency = competency;
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

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
