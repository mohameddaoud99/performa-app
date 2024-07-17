package tn.telnet.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telnet.workflow.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Boolean existsByName(String name);

    Boolean existsByColor(String color);


}