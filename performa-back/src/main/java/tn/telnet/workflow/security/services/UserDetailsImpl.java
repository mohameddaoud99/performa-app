package tn.telnet.workflow.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tn.telnet.workflow.models.Permission;
import tn.telnet.workflow.models.User;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String firstname;

    private String lastname;

    private String email;


    @JsonIgnore
    private String password;

    private String role;

    private Set<String> permissions;

    //private Set<Permission> permissions ;

    public UserDetailsImpl(Long id, String firstname, String lastname, String email, String password,
                           String role, Set<String> permissions ) {
        this.id = id;
        this.firstname = firstname;
        this.lastname=lastname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.permissions=permissions;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public static UserDetailsImpl build(User user) {
        String role = user.getRole().getName();
        Set<String> permissions = user.getRole().getPermissions().stream()
                .map(Permission::getName)
                .collect(Collectors.toSet());

        return new UserDetailsImpl(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                role,
                permissions
        );
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return firstname+" "+lastname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
