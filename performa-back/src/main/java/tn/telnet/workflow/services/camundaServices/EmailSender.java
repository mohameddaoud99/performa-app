package tn.telnet.workflow.services.camundaServices;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telnet.workflow.models.User;
import tn.telnet.workflow.repository.UserRepository;
import tn.telnet.workflow.services.EmailService;

@Service
public class EmailSender implements JavaDelegate {

    @Autowired
    EmailService emailService;
    private List<String> listEmails;
    private ProcessEngine processEngine;

    public void startProcess() {
        processEngine.getRuntimeService().startProcessInstanceById("Process_1sm92jk:1:5efbe21b-003f-11ef-aba6-002315f9abe2");
    }

    public EmailSender(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    public void setListEmails(List<String> listEmails) {
        this.listEmails = listEmails;
    }

    public void sendEmailToMultipleUsers(List<String> listEmails, String subject, String content) {
        try {
            for (String emailAddress : listEmails) {
                emailService.sendEmailWithDetails(emailAddress, subject, content);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String emailContent;

        emailContent = "Hello,\n\nI hope this email finds you well. This is a friendly reminder regarding your upcoming annual performance evaluation. "
                + "\n\nPlease connect to PerformaApp to submit Form evaluation ."
                + "\n\nLink: http://localhost:4200 "
                + "\n\nKind regards.";

/////////////////////////  matensechhhhhhhh decommentihaaa /////////////////
        sendEmailToMultipleUsers(listEmails, "Hello", emailContent);
/////////////////////////  matensechhhhhhhh decommentihaaa /////////////////
    }

    public String getEmailFromBusinessKey(String businessKey) {
        return businessKey.split(":")[0];
    }


}