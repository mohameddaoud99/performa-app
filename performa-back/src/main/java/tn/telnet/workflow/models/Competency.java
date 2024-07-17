package tn.telnet.workflow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@Entity
@Table(name = "Competency")

public class Competency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 255)
    private String name;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_CategoryCompetency",referencedColumnName = "id")
    //@JsonIgnore
    private CategoryCompetency categoryCompetency;

    @OneToMany(mappedBy = "competency")
    @JsonIgnore
    private Set<ProfileCompetencies> profileCompetencies;

    @OneToMany(mappedBy = "competency")
    @JsonIgnore
    private Set<GradeCompetencies> gradeCompetencies;

    @OneToMany(mappedBy = "competency")
    @JsonIgnore
    private Set<EvaluationCompetencies> evaluationCompetencies;


    public Competency() {
    }
    public Competency(Long id) {
        this.id =id;
    }
    public Competency(String name , CategoryCompetency categoryCompetency) {
        this.name = name;
        this.categoryCompetency =categoryCompetency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryCompetency getCategoryCompetency() {
        return categoryCompetency;
    }

    public void setCategoryCompetency(CategoryCompetency categoryCompetency) {
        this.categoryCompetency = categoryCompetency;
    }

    public Set<ProfileCompetencies> getProfileCompetencies() {
        return profileCompetencies;
    }

    public void setProfileCompetencies(Set<ProfileCompetencies> profileCompetencies) {
        this.profileCompetencies = profileCompetencies;
    }

    public Set<GradeCompetencies> getGradeCompetencies() {
        return gradeCompetencies;
    }

    public void setGradeCompetencies(Set<GradeCompetencies> gradeCompetencies) {
        this.gradeCompetencies = gradeCompetencies;
    }

    public Set<EvaluationCompetencies> getEvaluationCompetencies() {
        return evaluationCompetencies;
    }

    public void setEvaluationCompetencies(Set<EvaluationCompetencies> evaluationCompetencies) {
        this.evaluationCompetencies = evaluationCompetencies;
    }
}
