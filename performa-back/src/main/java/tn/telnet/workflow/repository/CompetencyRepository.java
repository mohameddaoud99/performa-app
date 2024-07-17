package tn.telnet.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.telnet.workflow.models.Competency;
import tn.telnet.workflow.models.EmployeeEvaluation;

import java.util.List;

@Repository
public interface CompetencyRepository extends JpaRepository<Competency, Long> {


}