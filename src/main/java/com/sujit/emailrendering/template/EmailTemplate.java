package com.sujit.emailrendering.template;

import lombok.Data;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


public class EmailTemplate {
    private String subject;
    private String templateName;
    private Context context;

    public EmailTemplate(String subject, String templateName, Context context) {
        this.subject = subject;
        this.templateName = templateName;
        this.context = context;
    }

    public String getSubject() {
        return subject;
    }

    public String getTemplateName() {
        return templateName;
    }


    public String render(TemplateEngine templateEngine){
        return templateEngine.process(templateName,context);
    }


}
