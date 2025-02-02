package tn.telnet.workflow.services.camundaServices;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telnet.workflow.models.EmployeeEvaluation;
import tn.telnet.workflow.models.User;
import tn.telnet.workflow.payload.request.UserEvalutionRequest;
import tn.telnet.workflow.repository.EmployeeEvaluationRepository;
import tn.telnet.workflow.repository.UserRepository;
import tn.telnet.workflow.services.EmailService;

import java.util.Optional;

@Service
public class SendEmailDelegationToEmployee implements JavaDelegate {

    @Autowired
    EmailService emailService;
    @Autowired
    EmployeeEvaluationRepository employeeEvaluationRepository;
    @Autowired
    UserRepository userRepository;

    private ProcessEngine processEngine;

    public SendEmailDelegationToEmployee(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String email = execution.getProcessBusinessKey();
        UserEvalutionRequest employee = employeeEvaluationRepository.getLastEvaluationForUserByEmail(email);
        Long appraiserId = employee.getEmployeeEvaluation().getId_appraiser();
        Optional<User> appraiser = userRepository.findById(appraiserId);
        String appraiserName = appraiser.get().getFirstname() + " " + appraiser.get().getLastname();
        String emailContent = "I hope this email finds you well."
                + "\n\nYour evaluation is delegated to Mr/Ms: " + appraiserName + "."
                + "\n\nKind regards.";
        emailService.sendEmailWithDetails(email, "Hello", emailContent);
    }
}