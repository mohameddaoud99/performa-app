package tn.telnet.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telnet.workflow.models.CategoryCompetency;

@Repository
public interface CategoryCompetencyRepository extends JpaRepository<CategoryCompetency, Long> {


}