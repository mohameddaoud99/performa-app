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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EvaluationConfirmationEmail implements JavaDelegate {

    @Autowired
    EmailService emailService;
    @Autowired
    EmployeeEvaluationRepository employeeEvaluationRepository;

    private ProcessEngine processEngine;

    public EvaluationConfirmationEmail(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String email = execution.getProcessBusinessKey();
        LocalDateTime dateTime = LocalDateTime.parse(execution.getVariable("EvaluationDateReviwer").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        String outputDate = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String outputTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        String emailContent = "I hope this email finds you well."
                + "\nYour assessment has been approved. You are cordially invited to participate in a face-to-face evaluation session with your appraiser."
                + "\nDate of evaluation : " + outputDate + " at " + outputTime + "."
                + "\n\nKind regards.  ";
        emailService.sendEmailWithDetails(email, "Hello", emailContent);
    }
}