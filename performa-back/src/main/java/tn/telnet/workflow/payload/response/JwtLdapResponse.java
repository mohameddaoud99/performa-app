package tn.telnet.workflow.payload.response;

import javax.naming.Name;
import java.util.Set;

public class JwtLdapResponse {
    private String token;
    private String type = "Bearer";
    private Name id;
    private String username;
    private String email;



    public JwtLdapResponse(String accessToken, Name id, String username, String email) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;

    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Name getId() {
        return id;
    }

    public void setId(Name id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}