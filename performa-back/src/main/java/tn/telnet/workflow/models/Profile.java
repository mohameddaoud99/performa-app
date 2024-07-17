package tn.telnet.workflow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ProfileCompetencies> profileCompetencies;

    public Profile() {
    }
    public Profile(Long id)
    {
        this.id=id;
    }
    public Profile(String name ) {
        this.name = name;
    }

    public Profile(long id, String name) {
        this.id =id;
        this.name =name;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
        for(User u : users){
            u.setProfile(this);
        }
    }

    public Set<ProfileCompetencies> getProfileCompetencies() {
        return profileCompetencies;
    }

    public void setProfileCompetencies(Set<ProfileCompetencies> profileCompetencies) {
        this.profileCompetencies = profileCompetencies;
    }
}
