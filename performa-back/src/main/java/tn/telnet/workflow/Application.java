package tn.telnet.workflow;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.telnet.workflow.models.*;
import tn.telnet.workflow.services.*;

import java.sql.Date;
import java.util.*;

@SpringBootApplication
@EnableProcessApplication
@EnableScheduling
@RestController
//@EnableJpaRepositories(basePackages = "tn.telnet.workflow.repository")
public class Application implements CommandLineRunner {

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    CategoryCompetencyService categoryCompetencyService;

    @Autowired
    GradeService gradeService;
    @Autowired
    GradeCompetenciesService gradeCompetenciesService;
    @Autowired
    ProfileCompetenciesService profileCompetenciesService;

    @GetMapping("/")
    public String test() {
        return "test";
    }

    @Override
    public void run(String... args) throws Exception {
        addProfileCompetencies();
        addGradeCompetencies();
        addCategoryGradesAndGrades();
        addDepartments();
        addRolesAndPermissions();
        addUserAdmin();
    }

    public static void main(String... args) {

        SpringApplication.run(Application.class, args);
    }


    public void addProfileCompetencies() {
        List<ProfileCompetencies> profileCompetencies = profileCompetenciesService.getAllProfileCompetencies();
        if (profileCompetencies.isEmpty()) {
            Profile profile1 = new Profile(1L);
            Competency competency1 = new Competency(1L);
            Competency competency2 = new Competency(2L);

            profileCompetenciesService.createProfileCompetencies(new ProfileCompetencies(profile1, competency1, 20.00, "Competent", 0));
            profileCompetenciesService.createProfileCompetencies(new ProfileCompetencies(profile1, competency2, 20.00, "Competent", 0));
        }

    }


    public void addGradeCompetencies() {
        List<GradeCompetencies> gradeCompetencies = gradeCompetenciesService.getAllGradeCompetencies();
        if (gradeCompetencies.isEmpty()) {
            Grade grade1 = new Grade(1L);
            Competency competency1 = new Competency(1L);
            Competency competency2 = new Competency(2L);

            gradeCompetenciesService.createGradeCompetencies(new GradeCompetencies(grade1, competency1, 20.00, "Competent", 0));
            gradeCompetenciesService.createGradeCompetencies(new GradeCompetencies(grade1, competency2, 20.00, "Competent", 0));
        }
    }

    public void addCategoryGradesAndGrades() {
        List<Grade> grades = gradeService.getAllGrades();
        List<CategoryGrade> category_grades = gradeService.getAllCategoryGrades();
        if (grades.isEmpty()) {
            Set<Grade> gradesList = new HashSet<>();


            Grade grade1 = new Grade("F1");
            Grade grade2 = new Grade("F2");
            Grade grade3 = new Grade("F3");
            Grade grade4 = new Grade("F4");
            gradesList.add(grade1);
            gradesList.add(grade2);
            gradeService.createGrade(grade1);
            gradeService.createGrade(grade2);
            gradeService.createGrade(grade3);
            gradeService.createGrade(grade4);

            if (category_grades.isEmpty()) {
                gradeService.createCategoryGrade(new CategoryGrade("Foundation", gradesList));
                gradeService.createCategoryGrade(new CategoryGrade("People Management path", gradesList));
                gradeService.createCategoryGrade(new CategoryGrade("Technical Management path", gradesList));
                gradeService.createCategoryGrade(new CategoryGrade("Leadership", gradesList));
            }
        }
    }

    public void addDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        if (departments.isEmpty()) {
            Department department1 = new Department(1L, EDepartment.HALL01);
            Department department2 = new Department(2L, EDepartment.HALL02);
            Department department3 = new Department(3L, EDepartment.HALL03);
            departmentService.createDepartment(department1);
            departmentService.createDepartment(department2);
            departmentService.createDepartment(department3);
        }
    }

    public void addRolesAndPermissions() {
        List<Role> roles = roleService.getAllRoles();
        List<Permission> permissions = roleService.getAllPermissions();
        Set<Permission> permissionsList = new HashSet<>();
        if (permissions.isEmpty()) {
            Permission usersManagement = new Permission(1, "users management");
            Permission rolesManagement = new Permission(2, "roles management");
            roleService.createPermission(usersManagement);
            roleService.createPermission(rolesManagement);
            permissionsList.add(usersManagement);
            permissionsList.add(rolesManagement);

            if (roles.isEmpty()) {
                roleService.createRole(new Role("ROLE_ADMIN", "Blue", permissionsList));
                roleService.createRole(new Role("ROLE_MANAGER", "Red", permissionsList));
                roleService.createRole(new Role("ROLE_EMPLOYEE", "Green", permissionsList));
            }
        }
    }

    public void addUserAdmin() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {

            Date date = new Date(1999 - 12 - 12);
            Role adminRole = new Role(1);
            Grade adminGrade = new Grade(1L);
            Profile profile = new Profile(1L, "People Management");
            User admin = new User("Admin", "Admin", "admin@gmail.com", "12345678", adminRole, "HALL01", adminGrade, "Team 1", date, profile);
            userService.createUser(admin);
        }
    }

}