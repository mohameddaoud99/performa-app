package tn.telnet.workflow.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService  {


    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public String sendMail(MultipartFile[] file, String to , String[] cc, String Subject, String body){
        return null;
    }

    public void sendEmailWithDetails(String email, String subject, String text) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject(subject);
        mail.setText(text);
        javaMailSender.send(mail);

    }

    //@Scheduled(cron = "1/30 * * * * *")
    public String sendAttachmentEmail() throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart);

        helper.setTo("mohamed.daoud99@hotmail.com");
        helper.setSubject("Test email with attachments");

        helper.setText("Hello, I'm testing email with attachments!");

        ClassPathResource file = new ClassPathResource("logoTelnet.png");
        helper.addInline("id101", file);

        javaMailSender.send(message);

        return "Email Sent!";
    }
}
