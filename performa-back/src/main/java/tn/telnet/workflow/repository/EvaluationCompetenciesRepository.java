package tn.telnet.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tn.telnet.workflow.models.EvaluationCompetencies;

@Repository
public interface EvaluationCompetenciesRepository extends JpaRepository<EvaluationCompetencies, Long> {

}