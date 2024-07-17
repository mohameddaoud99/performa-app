package tn.telnet.workflow.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.telnet.workflow.response.ResponseHandler;
import tn.telnet.workflow.services.GradeService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/api/grade")
@RestController
public class GradeController {

    @Autowired
    GradeService gradeService;

    @GetMapping("/getAllGrades")
    public ResponseEntity<Object> getAllGrades()
    {
        return ResponseHandler.responseBuilder("All grades",
                HttpStatus.OK, gradeService.getAllGrades());
    }
    @GetMapping("/getGradeById/{id}")
    public ResponseEntity<Object> getGradeById(@PathVariable long id)
    {
        return ResponseHandler.responseBuilder("grade with id: "+ id,
                HttpStatus.OK, gradeService.getGradeById(id));
    }

    @GetMapping("/getAllCategoryGrades")
    public ResponseEntity<Object> getAllCategoryGrades()
    {
        return ResponseHandler.responseBuilder("All grades",
                HttpStatus.OK, gradeService.getAllCategoryGrades());
    }
    @GetMapping("/getCategoryGradeById/{id}")
    public ResponseEntity<Object> getCategoryGradeById(@PathVariable long id)
    {
        return ResponseHandler.responseBuilder("category grade with id: "+ id,
                HttpStatus.OK, gradeService.getCategoryGradeById(id));
    }


}
