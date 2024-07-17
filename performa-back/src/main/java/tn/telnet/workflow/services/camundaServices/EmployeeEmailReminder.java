package tn.telnet.workflow.services.camundaServices;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telnet.workflow.services.EmailService;

@Service
public class EmployeeEmailReminder implements JavaDelegate {

    @Autowired
    EmailService emailService;

    private ProcessEngine processEngine;

    public EmployeeEmailReminder(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String email = execution.getProcessBusinessKey();
        String emailContent = "Hello,\n\nI hope this email finds you well. This is a friendly reminder regarding your upcoming annual performance evaluation. "
                + "\n\nPlease connect to PerformaApp to submit Form evaluation ."
                + "\n\nLink: http://localhost:4200 "
                + "\n\nKind regards.";
        //emailService.sendEmailWithDetails(email, "Hello", emailContent);
        System.out.println(email);
        System.out.println("timer is active");
    }


}