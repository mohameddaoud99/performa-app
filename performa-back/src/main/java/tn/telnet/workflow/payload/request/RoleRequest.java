package tn.telnet.workflow.payload.request;


import org.springframework.lang.Nullable;
import tn.telnet.workflow.models.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


public class RoleRequest {

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @Nullable
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    public RoleRequest(String name, @Nullable Set<User> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
