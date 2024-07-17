package tn.telnet.workflow.services.camundaServices;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telnet.workflow.payload.request.UserEvalutionRequest;
import tn.telnet.workflow.repository.EmployeeEvaluationRepository;
import tn.telnet.workflow.services.EmailService;

import java.sql.Date;

@Service
public class EvaluationRejectionEmail implements JavaDelegate {

    @Autowired
    EmailService emailService;
    @Autowired
    EmployeeEvaluationRepository employeeEvaluationRepository;

    private ProcessEngine processEngine;

    public EvaluationRejectionEmail(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String email = execution.getProcessBusinessKey();
        UserEvalutionRequest employee = employeeEvaluationRepository.getLastEvaluationForUserByEmail(email);
        String rejectionReasons = employee.getEmployeeEvaluation().getRejection_reasons();
        String emailContent = "I hope this email finds you well."
                + "\n\nIt may be due to some incomplete information that your evaluation is up for review."
                + "\nThese are the appraiser reasons of this review :"
                + "\n" + rejectionReasons
                + "\n\nKind regards.";
        emailService.sendEmailWithDetails(email, "Hello", emailContent);
    }
}