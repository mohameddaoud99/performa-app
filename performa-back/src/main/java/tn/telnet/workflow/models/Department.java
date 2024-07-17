package tn.telnet.workflow.models;


import jakarta.persistence.*;

@Entity

@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Department() {
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDepartment department_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department(Long id, EDepartment department_name) {
        this.id = id;
        this.department_name = department_name;
    }

    public EDepartment getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(EDepartment department_name) {
        this.department_name = department_name;
    }
}
