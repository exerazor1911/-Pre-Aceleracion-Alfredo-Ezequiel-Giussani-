package com.alkemy.pelis.pelis.service.impl;

import com.alkemy.pelis.pelis.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment env;

    @Value("${alkemy.pelis.email.sender}")
    private String emailSender;

    @Value("${alkemy.pelis.email.enabled}")
    private boolean enabled;

    @Override
    public void sendWelcomeEmailTo(String to) {
        if (!enabled) {
            return;
        }

        //TODO: set this environment key
        String apiKey = env.getProperty("EMAIL_API_KEY");

        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content(
                "text/plain",
                "Bienvenido/a a Alkemy Pelis"
        );
        String subject = "Alkemy Pelis";

        Mail mail = new Mail(fromEmail, subject,toEmail,content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println("Error trying to send the email");
        }
    }
}
