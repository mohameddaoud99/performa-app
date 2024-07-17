package tn.telnet.workflow.payload.request;

import tn.telnet.workflow.models.EmployeeEvaluation;
import tn.telnet.workflow.models.User;

import java.util.Date;


public class UserEvalutionRequest {

    private User user;
    private String status;

    private EmployeeEvaluation employeeEvaluation;

    public UserEvalutionRequest(User user, String status) {
        this.user = user;
        this.status = status;
    }

    public UserEvalutionRequest(User user, EmployeeEvaluation employeeEvaluation) {
        this.user = user;
        this.status = status;
        System.out.println("a");
        System.out.println(employeeEvaluation);
        this.employeeEvaluation = employeeEvaluation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EmployeeEvaluation getEmployeeEvaluation() {
        return employeeEvaluation;
    }

    public void setEmployeeEvaluation(EmployeeEvaluation employeeEvaluation) {
        this.employeeEvaluation = employeeEvaluation;
    }
}
