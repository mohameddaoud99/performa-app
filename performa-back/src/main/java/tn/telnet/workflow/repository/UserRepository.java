package tn.telnet.workflow.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.telnet.workflow.models.User;
import tn.telnet.workflow.payload.request.UserEvalutionRequest;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User getUserById(@Param("id") Long id);

    @Query("SELECT u FROM User u WHERE u.role.name !='ROLE_ADMIN' ")
    List<User> getUsersOnlyAdmins();

    @Query("SELECT u FROM User u WHERE u.department = :departmentName AND u.role.name !='ROLE_ADMIN'")
    List<User> getUsersByDepartment(@Param("departmentName") String departmentName);

    @Query("SELECT u FROM User u WHERE u.role.name = :roleName AND u.role.name !='ROLE_ADMIN'")
    List<User> getUsersByRole(@Param("roleName") String roleName);

    @Query("SELECT u FROM User u WHERE u.team = :team")
    List<User> getEmployeesByTeam(@Param("team") String team);

    @Query("SELECT u FROM User u WHERE u.profile.name = :profile")
    List<User> getEmployeesByProfile(@Param("profile") String profile);

    @Query("SELECT u FROM User u WHERE u.team = :team AND u.role.name = :role")
    List<User> findByRoleAndTeamAdmin(@Param("role") String role, @Param("team") String team);

    @Query("SELECT u FROM User u WHERE u.team = :team AND u.grade.job_title IN ('Program Manager','Project Manager','Senior Project Manager'," +
            "'Senior Program Manager', 'Program Director')")
    List<User> findAppraiserByTeam(@Param("team") String team);

    @Query("SELECT u FROM User u  WHERE u.grade.job_title IN ('Program Manager','Project Manager','Senior Project Manager'," +
            "'Senior Program Manager', 'Program Director')")
    List<User> findAllAppraisers();

    @Query("SELECT u FROM User u WHERE u.team = :team ")
    List<User> findByTeam(@Param("team") String team);

    @Query("SELECT u FROM User u WHERE u.email= :email")
    User getEmployeeByEmail(@Param("email") String email);

    @Query("SELECT new tn.telnet.workflow.payload.request.UserEvalutionRequest(u, e.status) FROM User u JOIN u.evaluations e WHERE u.team = :team AND u.role.name = :role AND e.createdAt IN (SELECT MAX(e2.createdAt) FROM EmployeeEvaluation e2 WHERE e2.user = u) GROUP BY u, e.status")
    List<UserEvalutionRequest> getLastEvaluationForEachUserByTeamAndRole(@Param("role") String role, @Param("team") String team);

}