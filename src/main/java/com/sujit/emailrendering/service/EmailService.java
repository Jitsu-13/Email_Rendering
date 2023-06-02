package com.sujit.emailrendering.service;

import com.sujit.emailrendering.model.User;
import com.sujit.emailrendering.template.EmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;


    public void sendEmail(User user, EmailTemplate emailTemplate) throws MessagingException {
        String subject = emailTemplate.getSubject();
        String body = emailTemplate.render(templateEngine);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(body, true);

        javaMailSender.send(message);
    }

}
