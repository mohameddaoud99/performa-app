package tn.telnet.workflow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Entity
@Table(name = "CategoryCompetency", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})

public class CategoryCompetency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;


    @OneToMany(mappedBy = "categoryCompetency", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Competency> competencies = new HashSet<>();


    public CategoryCompetency() {
    }
    public CategoryCompetency(String name) {
        this.name = name;
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


    public Set<Competency> getCompetencies() {
        return competencies;
    }
    public void setCompetencies(Set<Competency> competencies) {
        this.competencies = competencies;
        for (Competency c : competencies) {
            c.setCategoryCompetency(this);
        }
    }
}
