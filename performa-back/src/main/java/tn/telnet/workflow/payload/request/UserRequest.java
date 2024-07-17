package tn.telnet.workflow.payload.request;

import tn.telnet.workflow.models.Grade;
import tn.telnet.workflow.models.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

import java.sql.Date;

public class UserRequest {
    @NotBlank
    @Size(min = 2, max = 20)
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 20)
    private String lastname;


    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 2, max = 40)
    private String password;


    @NotBlank
    @Size(min = 2, max = 40)
    private String department;


    private Grade grade;

    @NotBlank
    @Size(min = 2, max = 40)
    private String team;

    private Date birthday;

    private Long id_appraiser;
    private Role role;


    public UserRequest(String firstname, String lastname, String email, String password, Role role,
                       String department, Grade grade, String team, Date birthday, Long id_appraiser) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.department = department;
        this.grade = grade;
        this.team = team;
        this.birthday = birthday;
        this.id_appraiser = id_appraiser;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserRequest() {
    }
}