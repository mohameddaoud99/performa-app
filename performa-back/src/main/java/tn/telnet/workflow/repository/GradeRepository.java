package tn.telnet.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.telnet.workflow.models.Grade;

public interface GradeRepository extends JpaRepository<Grade,Long> {


}
