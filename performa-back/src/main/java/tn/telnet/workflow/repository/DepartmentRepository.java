package tn.telnet.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telnet.workflow.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
