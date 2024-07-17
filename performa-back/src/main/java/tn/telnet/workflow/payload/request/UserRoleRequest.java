package tn.telnet.workflow.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import java.sql.Date;

public class UserRoleRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String firstname;

    @NotBlank
    @Size(min = 3, max = 20)
    private String lastname;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(min = 6, max = 40)
    private String jobTitle;

    @NotBlank
    @Size(min = 6, max = 40)
    private String department;

    @NotBlank
    @Size(min = 6, max = 40)
    private String grade;

    private Date birthday;

    private String role;
}
