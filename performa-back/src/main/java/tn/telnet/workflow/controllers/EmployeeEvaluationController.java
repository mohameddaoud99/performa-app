package tn.telnet.workflow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.telnet.workflow.models.EmployeeEvaluation;
import tn.telnet.workflow.models.EvaluationCompetencies;
import tn.telnet.workflow.models.Role;
import tn.telnet.workflow.payload.request.EvaluationCompetenciesRequest;
import tn.telnet.workflow.payload.request.UserEvalutionRequest;
import tn.telnet.workflow.repository.EmployeeEvaluationRepository;
import tn.telnet.workflow.response.ResponseHandler;
import tn.telnet.workflow.services.EmployeeEvaluationService;
import tn.telnet.workflow.services.EvaluationCompetenciesService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/api/employee_evaluation")
@RestController


public class EmployeeEvaluationController {
    @Autowired
    EmployeeEvaluationService employeeEvaluationService;
    @Autowired
    EmployeeEvaluationRepository employeeEvaluationRepository;
    @Autowired
    EvaluationCompetenciesService evaluationCompetenciesService;


    @GetMapping("/testHatem")
    public ResponseEntity<Object> testHatem(@RequestBody EmployeeEvaluation evaluation) {
        UserEvalutionRequest employee = employeeEvaluationRepository.getLastEvaluationForUserByEmail("mohamed.daoud99@hotmail.com");

        return ResponseHandler.responseBuilder("Evaluation saved as draft mode successfully",
                HttpStatus.OK, employee);
    }

    @PostMapping("/create")

    public ResponseEntity<Object> addEvaluation(@RequestBody EmployeeEvaluation evaluation) {
        if (evaluation.getEvaluationCompetencies() == null) {
            return ResponseHandler.responseBuilder("EvaluationCompetencies is null", HttpStatus.BAD_REQUEST, null);
        }

        EmployeeEvaluation savedEvaluation = employeeEvaluationService.createEvaluation(evaluation);


        List<EvaluationCompetencies> evaluationCompetencies = evaluation.getEvaluationCompetencies();
        for (EvaluationCompetencies ec : evaluationCompetencies) {
            ec.setEvaluation(savedEvaluation);
        }
        List<EvaluationCompetencies> savedEvaluationCompetencies = evaluationCompetenciesService.createListEvaluationCompetencies(evaluationCompetencies);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("evaluation", savedEvaluation);
        responseData.put("evaluationCompetencies", savedEvaluationCompetencies);

        return ResponseHandler.responseBuilder("Evaluation saved as draft mode successfully",
                HttpStatus.OK, responseData);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateEvaluation(@RequestBody EmployeeEvaluation evaluation) {
        EmployeeEvaluation existingEvaluation = employeeEvaluationService.getEvaluationById(evaluation.getId());
        if (existingEvaluation == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evaluation not found with ID: " + evaluation.getId());
        }
        existingEvaluation.setStatus(evaluation.getStatus());
        EmployeeEvaluation savedEvaluation = employeeEvaluationService.updateEvaluation(existingEvaluation);

        List<EvaluationCompetencies> evaluationCompetencies = evaluation.getEvaluationCompetencies();
        for (EvaluationCompetencies ec : evaluationCompetencies) {
            ec.setEvaluation(savedEvaluation);
        }
        List<EvaluationCompetencies> savedEvaluationCompetencies = evaluationCompetenciesService.updateListEvaluationCompetencies(evaluationCompetencies);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("evaluation", savedEvaluation);
        responseData.put("evaluationCompetencies", savedEvaluationCompetencies);

        return ResponseHandler.responseBuilder("Evaluation updated successfully",
                HttpStatus.OK, responseData);
    }

    @PostMapping("/submit")
    public ResponseEntity<Object> submitEvaluation(@RequestBody EmployeeEvaluation evaluation) {
        if (evaluation.getEvaluationCompetencies() == null) {
            return ResponseHandler.responseBuilder("EvaluationCompetencies is null", HttpStatus.BAD_REQUEST, null);
        }
        EmployeeEvaluation savedEvaluation = employeeEvaluationService.submitEvaluation(evaluation);
        List<EvaluationCompetencies> evaluationCompetencies = evaluation.getEvaluationCompetencies();
        for (EvaluationCompetencies ec : evaluationCompetencies) {
            ec.setEvaluation(savedEvaluation);
        }
        List<EvaluationCompetencies> savedEvaluationCompetencies = evaluationCompetenciesService.createListEvaluationCompetencies(evaluationCompetencies);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("evaluation", savedEvaluation);
        responseData.put("evaluationCompetencies", savedEvaluationCompetencies);

        return ResponseHandler.responseBuilder("Evaluation submitted successfully",
                HttpStatus.OK, responseData);
    }

    @PostMapping("/updateAndSubmit")
    public ResponseEntity<Object> updateAndSubmitEvaluation(@RequestBody EmployeeEvaluation evaluation) {
        if (evaluation.getEvaluationCompetencies() == null) {
            return ResponseHandler.responseBuilder("EvaluationCompetencies is null", HttpStatus.BAD_REQUEST, null);
        }
        List<EvaluationCompetencies> evaluationCompetencies = evaluation.getEvaluationCompetencies();
        EmployeeEvaluation savedEvaluation = employeeEvaluationService.submitEvaluation(evaluation);

        for (EvaluationCompetencies ec : evaluationCompetencies) {
            ec.setEvaluation(savedEvaluation);
        }
        List<EvaluationCompetencies> savedEvaluationCompetencies = evaluationCompetenciesService.updateListEvaluationCompetencies(evaluationCompetencies);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("evaluation", savedEvaluation);
        responseData.put("evaluationCompetencies", savedEvaluationCompetencies);

        return ResponseHandler.responseBuilder("Evaluation updated and submitted successfully", HttpStatus.OK, responseData);
    }

    ////////////////////////////////////////////////////////////

    @PostMapping("/saveEvaluationReviewerStep")
    public ResponseEntity<Object> saveEvaluationReviewerStep(@RequestBody EmployeeEvaluation evaluation) {
        if (evaluation.getEvaluationCompetencies() == null) {
            return ResponseHandler.responseBuilder("EvaluationCompetencies is null", HttpStatus.BAD_REQUEST, null);
        }
        EmployeeEvaluation savedEvaluation = employeeEvaluationService.saveEvaluationByReviewer(evaluation);
        List<EvaluationCompetencies> evaluationCompetencies = evaluation.getEvaluationCompetencies();
        for (EvaluationCompetencies ec : evaluationCompetencies) {
            ec.setEvaluation(savedEvaluation);
        }
        List<EvaluationCompetencies> savedEvaluationCompetencies = evaluationCompetenciesService.createListEvaluationCompetencies(evaluationCompetencies);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("evaluation", savedEvaluation);
        responseData.put("evaluationCompetencies", savedEvaluationCompetencies);

        return ResponseHandler.responseBuilder("Evaluation saved as draft mode successfully",
                HttpStatus.OK, responseData);
    }

    @PostMapping("/updateEvaluationReviewerStep")
    public ResponseEntity<Object> updateEvaluationReviewerStep(@RequestBody EmployeeEvaluation evaluation) {
        if (evaluation.getEvaluationCompetencies() == null) {
            return ResponseHandler.responseBuilder("EvaluationCompetencies is null", HttpStatus.BAD_REQUEST, null);
        }
        EmployeeEvaluation savedEvaluation = employeeEvaluationService.updateEvaluationByReviewer(evaluation);
        List<EvaluationCompetencies> evaluationCompetencies = evaluation.getEvaluationCompetencies();
        for (EvaluationCompetencies ec : evaluationCompetencies) {
            ec.setEvaluation(savedEvaluation);
        }
        List<EvaluationCompetencies> savedEvaluationCompetencies = evaluationCompetenciesService.createListEvaluationCompetencies(evaluationCompetencies);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("evaluation", savedEvaluation);
        responseData.put("evaluationCompetencies", savedEvaluationCompetencies);

        return ResponseHandler.responseBuilder("Evaluation updated as draft mode successfully",
                HttpStatus.OK, responseData);
    }


    @PostMapping("/EvaluationValidationByReviewerStep")
    public ResponseEntity<Object> EvaluationValidationByReviewerStep(@RequestBody EmployeeEvaluation evaluation) {
        if (evaluation.getEvaluationCompetencies() == null) {
            return ResponseHandler.responseBuilder("EvaluationCompetencies is null", HttpStatus.BAD_REQUEST, null);
        }
        List<EvaluationCompetencies> evaluationCompetencies = evaluation.getEvaluationCompetencies();
        EmployeeEvaluation savedEvaluation = employeeEvaluationService.evaluationValidationByReviewerStep(evaluation);

        for (EvaluationCompetencies ec : evaluationCompetencies) {
            ec.setEvaluation(savedEvaluation);
        }
        List<EvaluationCompetencies> savedEvaluationCompetencies = evaluationCompetenciesService.updateListEvaluationCompetencies(evaluationCompetencies);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("evaluation", savedEvaluation);
        responseData.put("evaluationCompetencies", savedEvaluationCompetencies);

        return ResponseHandler.responseBuilder("Evaluation validated successfully",
                HttpStatus.OK, responseData);
    }

    @PostMapping("/submitReviewerStep")
    public ResponseEntity<Object> submitReviewerStep(@RequestBody EmployeeEvaluation evaluation) {
        if (evaluation.getEvaluationCompetencies() == null) {
            return ResponseHandler.responseBuilder("EvaluationCompetencies is null", HttpStatus.BAD_REQUEST, null);
        }
        List<EvaluationCompetencies> evaluationCompetencies = evaluation.getEvaluationCompetencies();
        EmployeeEvaluation savedEvaluation = employeeEvaluationService.submitReviewerStep(evaluation);

        for (EvaluationCompetencies ec : evaluationCompetencies) {
            ec.setEvaluation(savedEvaluation);
        }
        List<EvaluationCompetencies> savedEvaluationCompetencies = evaluationCompetenciesService.updateListEvaluationCompetencies(evaluationCompetencies);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("evaluation", savedEvaluation);
        responseData.put("evaluationCompetencies", savedEvaluationCompetencies);

        return ResponseHandler.responseBuilder("Evaluation submitted successfully",
                HttpStatus.OK, responseData);
    }

    @PostMapping("/rejectEmployeeEvaluation")
    public ResponseEntity<Object> rejectEmployeeEvaluation(@RequestBody EmployeeEvaluation evaluation) {
        if (evaluation.getEvaluationCompetencies() == null) {
            return ResponseHandler.responseBuilder("EvaluationCompetencies is null", HttpStatus.BAD_REQUEST, null);
        }
        List<EvaluationCompetencies> evaluationCompetencies = evaluation.getEvaluationCompetencies();
        EmployeeEvaluation savedEvaluation = employeeEvaluationService.updateEvaluation(evaluation);

        for (EvaluationCompetencies ec : evaluationCompetencies) {
            ec.setEvaluation(savedEvaluation);
        }
        List<EvaluationCompetencies> savedEvaluationCompetencies = evaluationCompetenciesService.updateListEvaluationCompetencies(evaluationCompetencies);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("evaluation", savedEvaluation);
        responseData.put("evaluationCompetencies", savedEvaluationCompetencies);

        return ResponseHandler.responseBuilder("Employee evaluation rejected successfully",
                HttpStatus.OK, responseData);
    }

    @PostMapping("/delegateEmployeeEvaluation")
    public ResponseEntity<Object> delegateEvaluation(@RequestBody EmployeeEvaluation evaluation) {
        if (evaluation.getEvaluationCompetencies() == null) {
            return ResponseHandler.responseBuilder("EvaluationCompetencies is null", HttpStatus.BAD_REQUEST, null);
        }
        EmployeeEvaluation delegatedEvaluation = employeeEvaluationService.delegateEvaluation(evaluation);
        return ResponseHandler.responseBuilder("Employee evaluation delegated successfully", HttpStatus.OK, delegatedEvaluation);
    }

    @GetMapping("/getEvaluationById/{id}")
    public EmployeeEvaluation getUserById(@PathVariable("id") Long id) {
        return employeeEvaluationService.getEvaluationById(id);
    }

    @GetMapping("/getEvaluationsByEmployee/{id}")
    public ResponseEntity<Object> getEvaluationsByEmployee(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Evalautions of employee id: " + id,
                HttpStatus.OK, employeeEvaluationService.getEvaluationsByEmployee(id));
    }

    @GetMapping("/getEvaluationsByEmployeeSortedByDate/{id}")
    public ResponseEntity<Object> getEvaluationsByEmployeeSortedByDate(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Evaluations of employee id: " + id,
                HttpStatus.OK, employeeEvaluationService.getEvaluationsByEmployeeSortedByDate(id));
    }

    @GetMapping("/getCountDraftEvaluations")
    public Long getCountDraftEvaluations() {
        return employeeEvaluationService.getCountDraftEvaluations();
    }

    @GetMapping("/getEmployeeEvaluationsByAppraiser/{id}")
    public ResponseEntity<Object> getEmployeeByAppraiser(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Evaluations by appraiser: ",
                HttpStatus.OK, employeeEvaluationService.getEmployeeByAppraiser(id));
    }

    @GetMapping("/getLastEvaluationForUserByEmail/{email}")
    public ResponseEntity<Object> getLastEvaluationForUserByEmail(@PathVariable("email") String email) {
        UserEvalutionRequest user = employeeEvaluationRepository.getLastEvaluationForUserByEmail(email);
        return ResponseHandler.responseBuilder("All users",
                HttpStatus.OK, user);
    }


}

