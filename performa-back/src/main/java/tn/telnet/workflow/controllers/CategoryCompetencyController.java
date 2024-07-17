package tn.telnet.workflow.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.telnet.workflow.response.ResponseHandler;
import tn.telnet.workflow.services.CategoryCompetencyService;
import tn.telnet.workflow.services.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/api/Categorycompetency")
@RestController
public class CategoryCompetencyController {

    @Autowired
    CategoryCompetencyService categoryCompetencyService;

    @Autowired
    UserService userService;


    @GetMapping("/getAllCategoryCompetencies")
    public ResponseEntity<Object> getAllCompetencies() {
        return ResponseHandler.responseBuilder("All category competencies",
                HttpStatus.OK, categoryCompetencyService.getAllCategoryCompetencies());
    }

}
