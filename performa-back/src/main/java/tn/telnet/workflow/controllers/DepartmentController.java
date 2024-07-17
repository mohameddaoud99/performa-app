package tn.telnet.workflow.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.telnet.workflow.models.Department;
import tn.telnet.workflow.models.Role;
import tn.telnet.workflow.response.ResponseHandler;
import tn.telnet.workflow.services.DepartmentService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/getAllDepartments")
    public ResponseEntity<Object> getAllDepartments() {
        return ResponseHandler.responseBuilder("All departments",
                HttpStatus.OK, departmentService.getAllDepartments());
    }


}
