package tn.telnet.workflow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String gradeName;

    @OneToMany(mappedBy = "grade", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    private String job_title ;

    @OneToMany(mappedBy = "grade")
    @JsonIgnore
    private Set<GradeCompetencies> gradeCompetencies;

    public Grade(){
    }
    public Grade(Long id){
        this.id = id;
    }

    public Grade( String gradeName) {
        this.gradeName = gradeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
        for(User u : users){
            u.setGrade(this);
        }
    }

    public Set<GradeCompetencies> getGradeCompetencies() {
        return gradeCompetencies;
    }

    public void setGradeCompetencies(Set<GradeCompetencies> gradeCompetencies) {
        this.gradeCompetencies = gradeCompetencies;
    }
}
