package tn.telnet.workflow.models;

import com.fasterxml.jackson.annotation.*;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.DefaultValue;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    @Size(max = 50)
    private String firstname;

    @NotBlank
    @Size(max = 50)
    private String lastname;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    //@JsonIgnores
    @NotBlank
    @Size(max = 255)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<EmployeeEvaluation> evaluations = new HashSet<>();


    @NotBlank
    @Size(max = 100)
    private String department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grade", referencedColumnName = "id")
    private Grade grade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profile", referencedColumnName = "id")
    private Profile profile;

    @NotBlank
    @Size(max = 20)
    private String team;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @Nullable
    @DefaultValue("Not started")
    private String status = "Not started";

    private Long id_appraiser;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }


    public User(String firstname, String lastname, String email, String password,
                String department, String team, Date birthday, Long id_appraiser) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.department = department;
        this.team = team;
        this.birthday = birthday;
        this.id_appraiser = id_appraiser;
    }

    public User(String firstname, String lastname, String email, String password, Role role,
                String department, Grade grade, String team, Date birthday, Long id_appraiser, Profile profile) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.department = department;
        this.grade = grade;
        this.profile = profile;
        this.id_appraiser = id_appraiser;
        this.team = team;
        this.birthday = birthday;
    }

    public User(String firstname, String lastname, String email, String password, Role role,
                String department, Grade grade, String team, Date birthday, Profile profile) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.department = department;
        this.grade = grade;
        this.profile = profile;
        this.team = team;
        this.birthday = birthday;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public Set<EmployeeEvaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Set<EmployeeEvaluation> evaluations) {
        this.evaluations = evaluations;
        for (EmployeeEvaluation e : evaluations) {
            e.setUser(this);
        }
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getId_appraiser() {
        return id_appraiser;
    }

    public void setId_appraiser(Long id_appraiser) {
        this.id_appraiser = id_appraiser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}