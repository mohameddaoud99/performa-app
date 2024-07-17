package tn.telnet.workflow.services;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
//import tn.telnet.workflow.models.Delegation;
import tn.telnet.workflow.models.EmployeeEvaluation;
import tn.telnet.workflow.models.User;
import tn.telnet.workflow.payload.request.EmployeeEvaluationRequest;
import tn.telnet.workflow.repository.EmployeeEvaluationRepository;
import tn.telnet.workflow.repository.UserRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeEvaluationService {


    @Autowired
    EmployeeEvaluationRepository evaluationRepository;
    @Autowired
    UserRepository userRepository;


    public EmployeeEvaluation createEvaluation(@Valid @RequestBody EmployeeEvaluation evaluation) {

        evaluation.setDateEmployeeSignature(Date.valueOf(LocalDate.now()));
        evaluation.setDraftMode(true);
        evaluation.setSubmitted(false);
        evaluation.setReviwerStepDraftMode(false);
        evaluation.setReviwerStepeSubmitted(false);
        evaluation.setStatus("Waiting for employee self evaluation");
        return evaluationRepository.save(evaluation);
    }


    public EmployeeEvaluation updateEvaluation(@Valid @RequestBody EmployeeEvaluation evaluation) {

        evaluation.setDateEmployeeSignature(Date.valueOf(LocalDate.now()));
        evaluation.setDraftMode(true);
        evaluation.setSubmitted(false);
        evaluation.setStatus("Waiting for employee self evaluation");

        return evaluationRepository.save(evaluation);
    }

    public EmployeeEvaluation submitEvaluation(@Valid @RequestBody EmployeeEvaluation evaluation) {
        evaluation.setDateEmployeeSignature(Date.valueOf(LocalDate.now()));
        evaluation.setDraftMode(false);
        evaluation.setSubmitted(true);
        evaluation.setStatus("Waiting for appraiser validation");
        return evaluationRepository.save(evaluation);
    }

    public EmployeeEvaluation evaluationValidationByReviewerStep(@Valid @RequestBody EmployeeEvaluation evaluation) {
        evaluation.setDateAppraiserSignature(Date.valueOf(LocalDate.now()));
        evaluation.setDraftMode(false);
        evaluation.setSubmitted(true);
        evaluation.setStatus("Waiting for appraiser evaluation");
        return evaluationRepository.save(evaluation);
    }

    ////////////////////////////////////////////////////////////////////////////
    public EmployeeEvaluation saveEvaluationByReviewer(@Valid @RequestBody EmployeeEvaluation evaluation) {
        evaluation.setDateReviewerEvaluation(Timestamp.valueOf(LocalDateTime.now()));
        evaluation.setReviwerStepDraftMode(true);
        evaluation.setReviwerStepeSubmitted(false);

        evaluation.setStatus("Reviewer Step Draft Mode");
        return evaluationRepository.save(evaluation);
    }

    public EmployeeEvaluation updateEvaluationByReviewer(@Valid @RequestBody EmployeeEvaluation evaluation) {

        evaluation.setDateReviewerEvaluation(Timestamp.valueOf(LocalDateTime.now()));
        evaluation.setDraftMode(false);
        evaluation.setSubmitted(true);
        evaluation.setReviwerStepDraftMode(true);
        evaluation.setReviwerStepeSubmitted(false);
        evaluation.setStatus("Reviewer Step Draft Mode");

        return evaluationRepository.save(evaluation);
    }

    public EmployeeEvaluation submitReviewerStep(@Valid @RequestBody EmployeeEvaluation evaluation) {
        evaluation.setDateAppraiserSignature(Date.valueOf(LocalDate.now()));
        evaluation.setDraftMode(false);
        evaluation.setSubmitted(true);
        evaluation.setReviwerStepDraftMode(false);
        evaluation.setReviwerStepeSubmitted(true);
        evaluation.setStatus("Completed");
        return evaluationRepository.save(evaluation);
    }

    public EmployeeEvaluation delegateEvaluation(@Valid @RequestBody EmployeeEvaluation evaluation) {
        evaluation.setId_appraiser(evaluation.getId_appraiser());
        return evaluationRepository.save(evaluation);
    }

    public EmployeeEvaluation getEvaluationById(Long id) {
        return evaluationRepository.findById(id).get();
    }

    public List<EmployeeEvaluation> getEvaluationsByEmployee(Long id) {
        List<EmployeeEvaluation> evaluations = evaluationRepository.getEvaluationsByEmployee(id);
        return evaluations;
    }

    public List<EmployeeEvaluation> getAllEvaluations() {
        List<EmployeeEvaluation> evaluations = evaluationRepository.findAll();
        return evaluations;
    }

    public List<EmployeeEvaluationRequest> getEvaluationsByEmployeeSortedByDate(Long id) {
        List<EmployeeEvaluationRequest> evaluations = new ArrayList<>();
        List<EmployeeEvaluation> employeeEvaluations = evaluationRepository.getEvaluationsByEmployeeSortedByDate(id);
        for (EmployeeEvaluation e : employeeEvaluations) {
            User appraiser = userRepository.getUserById(e.getId_appraiser());
            evaluations.add(new EmployeeEvaluationRequest(
                    e.getId(),
                    appraiser,
                    e.getDateEmployeeSignature(),
                    e.getStatus(),
                    e.isDraftMode(),
                    e.isSubmitted()
            ));
        }
        return evaluations;
    }

    public Long getCountDraftEvaluations() {
        long countDraftEvaluations = evaluationRepository.countDraftEvaluations();
        return countDraftEvaluations;
    }

    public List<EmployeeEvaluation> getEmployeeByAppraiser(Long id) {
        List<EmployeeEvaluation> evaluations = evaluationRepository.getEvaluationsByAppraiser(id);
        return evaluations;
    }


}
