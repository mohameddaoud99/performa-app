package tn.telnet.workflow.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.telnet.workflow.exceptions.NotFoundException;
import tn.telnet.workflow.models.User;
import tn.telnet.workflow.payload.request.CompetencyCategoryAverageRequest;
import tn.telnet.workflow.payload.request.UserEvalutionRequest;
import tn.telnet.workflow.payload.request.UserRequest;
import tn.telnet.workflow.payload.response.JwtResponse;
import tn.telnet.workflow.payload.response.MessageResponse;
import tn.telnet.workflow.repository.UserRepository;
import tn.telnet.workflow.response.ResponseHandler;
import tn.telnet.workflow.services.DepartmentService;
import tn.telnet.workflow.services.EmailService;
import tn.telnet.workflow.services.RoleService;
import tn.telnet.workflow.services.UserService;
import tn.telnet.workflow.services.camundaServices.EmailSender;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    RoleService roleService;
    @Autowired
    EmailSender emailSender;
    @Autowired
    EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskService taskService;

    private final RuntimeService runtimeService;

    public UserController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }


    @PostMapping("/createUser")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")

    public ResponseEntity<Object> addUser(@RequestBody User user) {
        if (user.getRole() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "error that exists!");
        } else {
            return ResponseHandler.responseBuilder("Great, user added succefully!",
                    HttpStatus.OK, userService.createUser(user));
        }
    }

    @PostMapping("/updateUser")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        if (user.getRole() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "error that exists!");
        } else {
            return ResponseHandler.responseBuilder("Great, user updated succefully!",
                    HttpStatus.OK, userService.updateUser(user));
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseHandler.responseBuilder("All users",
                HttpStatus.OK, userService.getAllUsers());
    }

    @GetMapping("/getUsersOnlyAdmins")
    public ResponseEntity<Object> getUsersOnlyAdmins() {
        return ResponseHandler.responseBuilder("All users",
                HttpStatus.OK, userService.getUsersOnlyAdmins());
    }

    @GetMapping("/getUsers/{departmentName}")
    public ResponseEntity<Object> getUsersByDepartment(@PathVariable("departmentName") String departmentName) {
        List<User> users = userService.getUsersByDepartment(departmentName);
        return ResponseHandler.responseBuilder("All users",
                HttpStatus.OK, users);
    }

    @GetMapping("/getUsersByRole/{roleName}")
    public ResponseEntity<Object> getUsersByRole(@PathVariable("roleName") String roleName) {
        List<User> users = userService.getUsersByRole(roleName);
        return ResponseHandler.responseBuilder("All users",
                HttpStatus.OK, users);
    }

    @GetMapping("/getUsersByTeam/{team}")
    public List<User> getEmployeesByTeam(@PathVariable("team") String team) {
        List<User> employees = userService.getEmployeesByTeam(team);
        return employees;
    }

    @GetMapping("/getUsersByProfile/{profile}")
    public List<User> getEmployeesByProfile(@PathVariable("profile") String profile) {
        List<User> employees = userService.getEmployeesByProfile(profile);
        return employees;
    }

    @GetMapping("/getUsersByRoleAndTeam/{role}/{team}")
    public ResponseEntity<Object> getUsersByRoleAndTeam(@PathVariable("role") String role, @PathVariable("team") String team) {
        List<User> users = userService.getUsersByRoleAndTeam(role, team);
        return ResponseHandler.responseBuilder("All users",
                HttpStatus.OK, users);
    }

    @GetMapping("/getAppraisersByTeamAndProfile/{team}")
    public ResponseEntity<Object> getUsersByTeamAndProfile(@PathVariable("team") String team) {
        List<User> users = userService.getUsersByTeamAndProfile(team);
        return ResponseHandler.responseBuilder("All SPM(s) in team " + team,
                HttpStatus.OK, users);
    }

    @GetMapping("/getAllAppraisers")
    public ResponseEntity<Object> getAllAppraisers() {
        List<User> users = userService.getAllAppraisers();
        return ResponseHandler.responseBuilder("All appraisers ",
                HttpStatus.OK, users);
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getUserByEmail/{email}")
    public User getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }


    @GetMapping("/getAllDepartments")
    public ResponseEntity<Object> getAllDepartments() {
        return ResponseHandler.responseBuilder("All departments",
                HttpStatus.OK, departmentService.getAllDepartments());
    }

    /*@GetMapping("/getAllRoles")
    public ResponseEntity<Object> getAllRoles() {
        return ResponseHandler.responseBuilder("All roles",
                HttpStatus.OK, roleService.getAllRoles());
    }*/

    /*@GetMapping("/getAllJobTitles")
    public ResponseEntity<Object> getAllJobTitles() {
        return ResponseHandler.responseBuilder("All jobs",
                HttpStatus.OK, departmentService.getAllDepartments());
    }*/

    @GetMapping("/getAllProfiles")
    public ResponseEntity<Object> getAllProfiles() {
        return ResponseHandler.responseBuilder("All profiles",
                HttpStatus.OK, userService.getAllProfiles());
    }


    @GetMapping("/getLastEvaluationForEachUserByTeamAndRole/{role}/{team}")
    public ResponseEntity<Object> getLastEvaluationForEachUser(@PathVariable("role") String role, @PathVariable("team") String team) {
        List<UserEvalutionRequest> users = userRepository.getLastEvaluationForEachUserByTeamAndRole(role, team);
        return ResponseHandler.responseBuilder("All users",
                HttpStatus.OK, users);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable long id) {
        this.userService.deleteUser(id);
    }

    @GetMapping("/getAverageMarksForEachCompetencyPerCategoryPerUser/{id}")
    public List<CompetencyCategoryAverageRequest> getAverageMarksForEachCompetencyPerCategoryPerUser(@PathVariable("id") Long id) {
        List<CompetencyCategoryAverageRequest> marksList = userService.getAverageMarksForEachCompetencyPerCategoryPerUser(id);
        return marksList;
    }


    @PostMapping("/notifyUsers")
    public ResponseEntity<String> notifyUsers(@RequestBody List<String> emails) {

        try {
            ArrayList<String> errors = startProcessInstance(emails);
            ObjectMapper mapper = new ObjectMapper();
            String responseJson = "Process instances started successfully for all emails.";
            responseJson = mapper.writeValueAsString(errors);
            if (!errors.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("" + errors);
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseJson);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while starting process instances: " + e.getMessage());
        }
    }

    private ArrayList<String> startProcessInstance(List<String> emails) {
        ArrayList<String> errors = new ArrayList<>();
        emailSender.setListEmails(emails);
        for (String email : emails) {

            List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery()
                    .processInstanceBusinessKey(email)
                    .list();
            if (processInstances.size() == 0) {
                ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_1sm92jk", email);
                if (processInstance != null) {
                    String businessKey = processInstance.getBusinessKey();
                    String emaill = emailSender.getEmailFromBusinessKey(businessKey);
                    User assignedUser = userRepository.getEmployeeByEmail(emaill);
                    if (assignedUser != null) {
                        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
                        if (task != null) {
                            taskService.setAssignee(task.getId(), assignedUser.getEmail());
                        }
                    } else {
                        System.out.println("User not found for email: " + email);
                    }
                } else {
                    errors.add("Process instance not created for: " + email);
                }
            } else {
                User user = userRepository.getEmployeeByEmail(email);
                String userName = user != null ? user.getFirstname() + " " + user.getLastname() : "Unknown";
                errors.add("An evaluation process already exists for employee: " + userName);

            }
        }
        return errors;
    }
}
