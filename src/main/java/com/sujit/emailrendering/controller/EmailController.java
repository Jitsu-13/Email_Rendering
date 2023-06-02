package com.sujit.emailrendering.controller;

import com.sujit.emailrendering.model.User;
import com.sujit.emailrendering.service.EmailService;
import com.sujit.emailrendering.template.EmailTemplate;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;


    @PostMapping("/send-welcome-email")
    public ResponseEntity<String> sendWelcomeEmail(@RequestBody User user) throws MessagingException {
        EmailTemplate template = createWelcomeEmailTemplate(user);
        emailService.sendEmail(user, template);

        return ResponseEntity.ok("Welcome email sent successfully!");
    }

    @PostMapping("/send-mydeals-email")
    public ResponseEntity<String> sendMyDealsEmail(@RequestBody User user) throws MessagingException {
        EmailTemplate template = createMyDealsEmailTemplate(user);
        emailService.sendEmail(user, template);

        return ResponseEntity.ok("MyDeals email sent successfully!");
    }

    private EmailTemplate createWelcomeEmailTemplate(User user) {
        Context context = new Context();
        context.setVariable("user", user);

        return new EmailTemplate("Welcome to Our Application", "welcome-email", context);
    }

    private EmailTemplate createMyDealsEmailTemplate(User user) {
        Context context = new Context();
        context.setVariable("user", user);

        return new EmailTemplate("Check out our latest deals", "mydeals-email", context);
    }
}
