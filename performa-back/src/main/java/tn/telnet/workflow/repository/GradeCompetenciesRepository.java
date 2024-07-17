package tn.telnet.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.telnet.workflow.models.GradeCompetencies;

import java.util.List;

@Repository
public interface GradeCompetenciesRepository extends JpaRepository<GradeCompetencies, Long> {


    @Query("SELECT g FROM GradeCompetencies g WHERE g.grade.gradeName = :name")
    List<GradeCompetencies> getGradeCompetenciesByGradeName(@Param("name") String name);

}