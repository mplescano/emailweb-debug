package com.mplescano.webapp.emailweb.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EmailWebController {

    @Autowired
    private JavaMailSender mailSender;
    
    @PostMapping("/send")
    public String sendEmail(@RequestBody MailBean mail) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        if (mail.getPriority() != null) {
            helper.setPriority(mail.getPriority().intValue());        	
        }
        
        helper.setReplyTo(mail.getReplyTo());
        
        helper.setText(mail.getBody(), false);
        
        mailSender.send(message);
        
        return "ok";
    }
	
}
