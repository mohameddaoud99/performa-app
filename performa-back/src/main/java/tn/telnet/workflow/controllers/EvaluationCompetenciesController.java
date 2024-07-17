package tn.telnet.workflow.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.telnet.workflow.models.EmployeeEvaluation;
import tn.telnet.workflow.models.EvaluationCompetencies;
import tn.telnet.workflow.models.GradeCompetencies;
import tn.telnet.workflow.response.ResponseHandler;
import tn.telnet.workflow.services.EvaluationCompetenciesService;
import tn.telnet.workflow.services.GradeCompetenciesService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/api/evaluationCompetencies")
@RestController
public class EvaluationCompetenciesController {

    @Autowired
    EvaluationCompetenciesService evaluationCompetenciesService;

    @PostMapping("/create")
    public ResponseEntity<Object> addEvaluationCompetencies(@RequestBody EvaluationCompetencies evaluation) {
        return evaluationCompetenciesService.createEvaluationCompetencies(evaluation);
    }

    @GetMapping("/getAllEvaluationCompetencies")
    public List<EvaluationCompetencies> getAllEvaluationsCompetencies() {
        return evaluationCompetenciesService.getAllEvaluationCompetencies();
    }


}
