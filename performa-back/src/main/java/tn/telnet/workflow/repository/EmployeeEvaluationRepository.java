package tn.telnet.workflow.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.telnet.workflow.models.EmployeeEvaluation;
import tn.telnet.workflow.payload.request.CompetencyCategoryAverageRequest;
import tn.telnet.workflow.payload.request.UserEvalutionRequest;

import java.util.List;

@Repository
public interface EmployeeEvaluationRepository extends JpaRepository<EmployeeEvaluation, Long> {

    @Query("SELECT e FROM EmployeeEvaluation e WHERE e.user.id = :id AND e.status='Completed' ")
    List<EmployeeEvaluation> getEvaluationsByEmployee(@Param("id") Long id);

    @Query("SELECT e FROM EmployeeEvaluation e WHERE e.user.id = :id ORDER BY e.createdAt desc ")
    List<EmployeeEvaluation> getEvaluationsByEmployeeSortedByDate(@Param("id") Long id);

    @Query("SELECT COUNT(e) FROM EmployeeEvaluation e WHERE e.isDraftMode = true and e.isSubmitted =false ")
    long countDraftEvaluations();

    @Query("SELECT e FROM EmployeeEvaluation e WHERE e.id_appraiser = :id ORDER BY e.createdAt desc ")
    List<EmployeeEvaluation> getEvaluationsByAppraiser(@Param("id") Long id);

    @Query("SELECT new tn.telnet.workflow.payload.request.UserEvalutionRequest(u, e) " +
            "FROM User u " +
            "JOIN u.evaluations e " +
            "WHERE u.email = :email " +
            "AND e.createdAt = (SELECT MAX(e2.createdAt) FROM EmployeeEvaluation e2 WHERE e2.user = u) ")
    UserEvalutionRequest getLastEvaluationForUserByEmail(@Param("email") String email);

    @Query("SELECT COUNT(e) FROM EmployeeEvaluation e WHERE e.user.id = :id")
    Integer getEvaluationsCountByEmployee(@Param("id") Long id);

    @Query("SELECT DISTINCT e.status FROM EmployeeEvaluation e")
    List<String> getDistinctStatus();

    @Query("SELECT COUNT(e) FROM EmployeeEvaluation e WHERE e.id_appraiser = :id AND e.status = :status")
    Integer getEmployeeNumberByStatusByAppraiser(@Param("id") Long id, @Param("status") String status);

    @Query("SELECT COUNT(e) FROM EmployeeEvaluation e WHERE e.user.department = :department AND e.status = :status")
    Integer getEmployeeNumberByStatusByDepartment(@Param("department") String department, @Param("status") String status);

    @Query("SELECT COUNT(e) FROM EmployeeEvaluation e WHERE e.user.role.name = :role AND e.status = :status")
    Integer getEmployeeNumberByStatusByRole(@Param("role") String role, @Param("status") String status);

    @Query("SELECT COUNT(e) FROM EmployeeEvaluation e WHERE   e.status = :status")
    Integer getAllEmployeeNumberByStatus(@Param("status") String status);

    @Query("SELECT new tn.telnet.workflow.payload.request.CompetencyCategoryAverageRequest(ec.competency.categoryCompetency.id, ec.competency.categoryCompetency.name, ec.competency.id, ec.competency.name, AVG(ec.points)) " +
            "FROM EvaluationCompetencies ec " +
            "JOIN ec.evaluation e " +
            "WHERE e.user.id = :userId " +
            "GROUP BY ec.competency.categoryCompetency.id, ec.competency.categoryCompetency.name, ec.competency.id, ec.competency.name"
    )
    List<CompetencyCategoryAverageRequest> getAverageMarksForEachCompetencyPerCategoryPerUser(@Param("userId") Long userId);


}
