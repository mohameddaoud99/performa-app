package tn.telnet.workflow.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import tn.telnet.workflow.exceptions.NotFoundException;
import tn.telnet.workflow.models.*;
import tn.telnet.workflow.payload.request.CompetencyCategoryAverageRequest;
import tn.telnet.workflow.payload.request.UserRequest;
import tn.telnet.workflow.payload.response.MessageResponse;
import tn.telnet.workflow.repository.EmployeeEvaluationRepository;
import tn.telnet.workflow.repository.ProfileRepository;
import tn.telnet.workflow.repository.RoleRepository;
import tn.telnet.workflow.repository.UserRepository;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    private EmployeeEvaluationRepository evaluationRepository;

    @Autowired
    PasswordEncoder encoder;

    public ResponseEntity<?> createUser(@RequestBody User userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        User user = new User(userRequest.getFirstname(), userRequest.getLastname(),
                userRequest.getEmail(),
                encoder.encode(userRequest.getPassword()),
                userRequest.getDepartment(),
                userRequest.getTeam(),
                userRequest.getBirthday(),
                userRequest.getId_appraiser()
        );
        Role role = userRequest.getRole();
        Grade grade = userRequest.getGrade();
        Profile profile = userRequest.getProfile();
        user.setRole(role);
        user.setGrade(grade);
        user.setProfile(profile);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User added successfully!"));

    }

    public ResponseEntity<?> updateUser(@RequestBody User userRequest) {
        Optional<User> userOptional = userRepository.findById(userRequest.getId());

        if (!userOptional.isPresent()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        User user = userOptional.get();
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setEmail(userRequest.getEmail());
        //user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        user.setGrade(userRequest.getGrade());
        user.setProfile(userRequest.getProfile());
        user.setDepartment(userRequest.getDepartment());
        user.setTeam(userRequest.getTeam());
        user.setBirthday(userRequest.getBirthday());
        user.setId_appraiser(userRequest.getId_appraiser());

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public List<User> getUsersOnlyAdmins() {
        List<User> users = userRepository.getUsersOnlyAdmins();
        return users;
    }

    public List<User> getUsersByDepartment(String departmentName) {
        List<User> users = userRepository.getUsersByDepartment(departmentName);
        if (users.isEmpty()) {
            throw new NotFoundException("No users found in the department: " + departmentName);
        }
        return users;
    }

    public List<User> getUsersByRole(String roleName) {
        List<User> users = userRepository.getUsersByRole(roleName);
        if (users.isEmpty()) {
            throw new NotFoundException("No users found with role: " + roleName);
        }
        return users;
    }

    public List<User> getEmployeesByTeam(String team) {
        List<User> employees = userRepository.getEmployeesByTeam(team);
        if (employees.isEmpty()) {
            throw new NotFoundException("No users found in team: " + team);
        }
        return employees;
    }

    public List<User> getEmployeesByProfile(String profile) {
        List<User> employees = userRepository.getEmployeesByProfile(profile);
        if (employees.isEmpty()) {
            throw new NotFoundException("No employees found with profile: " + profile);
        }
        return employees;
    }


    public List<User> getUsersByRoleAndTeam(String role, String team) {
        List<User> users = userRepository.findByRoleAndTeamAdmin(role, team);
        if (users.isEmpty()) {
            throw new NotFoundException("No users found in: " + team);
        }
        return users;
    }

    public List<User> getUsersByTeamAndProfile(String team) {
        List<User> users = userRepository.findAppraiserByTeam(team);
        if (users.isEmpty()) {
            throw new NotFoundException("No SPM found in: " + team);
        }
        return users;
    }

    public List<User> getAllAppraisers() {
        List<User> users = userRepository.findAllAppraisers();
        if (users.isEmpty()) {
            throw new NotFoundException("No appraiser found : ");
        }
        return users;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public List<Profile> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles;
    }

    public void deleteUser(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not exist");
        }
        userRepository.deleteById(id);
    }

    public List<CompetencyCategoryAverageRequest> getAverageMarksForEachCompetencyPerCategoryPerUser(Long id) {
        List<CompetencyCategoryAverageRequest> marksList = evaluationRepository.getAverageMarksForEachCompetencyPerCategoryPerUser(id);
        return marksList;
    }

}
