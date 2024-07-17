package tn.telnet.workflow.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.telnet.workflow.exceptions.NotFoundException;
import tn.telnet.workflow.models.Permission;
import tn.telnet.workflow.models.Role;
import tn.telnet.workflow.payload.request.RoleRequest;
import tn.telnet.workflow.payload.response.MessageResponse;
import tn.telnet.workflow.repository.PermissionRepository;
import tn.telnet.workflow.repository.RoleRepository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public ResponseEntity<?> createRole(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Role name is already taken!"));
        }

        Set<Permission> permissions = role.getPermissions();
        Iterator<Permission> i = permissions.iterator();
        roleRepository.save(role);
        return ResponseEntity.ok(new MessageResponse("Role added successfully!"));
    }

    public ResponseEntity<?> updateRole(Role role) {
        roleRepository.save(role);
        return ResponseEntity.ok(new MessageResponse("Role upated successfully!"));
    }

    public Role getRoleById(Long idRole) {
        return roleRepository.findById(idRole).get();
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public String deleteRole(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "role not exist");
        }
        roleRepository.deleteById(id);
        return "Role deleted succefully";
    }

    public ResponseEntity<?> createPermission(Permission permission) {
        if (permissionRepository.existsByName(permission.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: permission name is already taken!"));
        }
        permissionRepository.save(permission);
        return ResponseEntity.ok(new MessageResponse("Permission added successfully!"));
    }

    public List<Permission> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions;
    }

    public boolean isRoleNameExist(String name) {
        return this.roleRepository.existsByName(name);
    }

    public boolean isRoleColorExist(String color) {
        return this.roleRepository.existsByColor(color);
    }
}
