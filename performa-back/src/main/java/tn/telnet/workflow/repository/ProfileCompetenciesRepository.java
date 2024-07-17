package tn.telnet.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.telnet.workflow.models.GradeCompetencies;
import tn.telnet.workflow.models.ProfileCompetencies;

import java.util.List;

@Repository
public interface ProfileCompetenciesRepository extends JpaRepository<ProfileCompetencies, Long> {

    @Query("SELECT p FROM ProfileCompetencies p WHERE p.profile.name = :name")
    List<ProfileCompetencies> getProfileCompetenciesByName(@Param("name") String name);

    @Query("SELECT COUNT(e) FROM ProfileCompetencies e WHERE e.profile.id = :id")
    Integer getCompetenciesCountByEmployee(@Param("id") Long id);
}