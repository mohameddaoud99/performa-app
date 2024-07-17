package tn.telnet.workflow.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.telnet.workflow.models.Permission;
import tn.telnet.workflow.models.Role;
import tn.telnet.workflow.payload.request.RoleRequest;
import tn.telnet.workflow.payload.request.UserRequest;
import tn.telnet.workflow.payload.response.MessageResponse;
import tn.telnet.workflow.response.ResponseHandler;
import tn.telnet.workflow.services.RoleService;
import tn.telnet.workflow.services.UserService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/api/role")
@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/createRole")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")

    public ResponseEntity<Object> addRole(@RequestBody @Valid Role role) {
        if (role.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "error that exists!");
        }
        if (this.roleService.isRoleNameExist(role.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Role name is already taken!"));
        }
        if (this.roleService.isRoleColorExist(role.getColor())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Role color is already taken!"));
        } else {
            return ResponseHandler.responseBuilder("Great, role added succefully!",
                    HttpStatus.OK, roleService.createRole(role));
        }
    }

    @PostMapping("/updateRole")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateRole(@RequestBody @Valid Role newRole) {
        Role role = roleService.getRoleById(newRole.getId());
        if (role == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "error that exists!");
        }
        if (!role.getName().toLowerCase().trim().equals(newRole.getName().toLowerCase().trim()) & this.roleService.isRoleNameExist(newRole.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Role name is already taken!"));
        }
        if (!role.getColor().toLowerCase().trim().equals(newRole.getColor().toLowerCase().trim()) & this.roleService.isRoleColorExist(newRole.getColor())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Role color is already taken!"));
        }
        role.setName(newRole.getName());
        role.setColor(newRole.getColor());
        role.setPermissions(newRole.getPermissions());
        return ResponseHandler.responseBuilder("Great, role updated succefully!",
                HttpStatus.OK, roleService.updateRole(role));
    }

    @GetMapping("/getRoleById/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @GetMapping("/getAllRoles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("deleteRole/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable long id) {
        return ResponseHandler.responseBuilder("role deleted ",
                HttpStatus.OK, roleService.deleteRole(id));
    }

    @GetMapping("/getAllPermissions")
    public List<Permission> getAllPermissions() {
        return roleService.getAllPermissions();
    }

}
