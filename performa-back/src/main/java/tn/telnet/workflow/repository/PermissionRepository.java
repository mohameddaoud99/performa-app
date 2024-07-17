package tn.telnet.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.telnet.workflow.models.Permission;

public interface PermissionRepository  extends JpaRepository<Permission, Long> {
    Boolean existsByName(String name);

}
