package tn.telnet.workflow.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "permissions", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    Set<Role> roles;

    public String getName() {
        return name;
    }

    public Permission() {
    }

    public Permission(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
