package tn.telnet.workflow.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;


@Entity
    @Table(name = "profile_competencies")
    public class ProfileCompetencies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    @JsonIgnore
    private Profile profile;

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


    public ProfileCompetencies() {}

    public ProfileCompetencies(Profile profile, Competency competency, Double weightage, String level, double points) {
        this.profile = profile;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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
