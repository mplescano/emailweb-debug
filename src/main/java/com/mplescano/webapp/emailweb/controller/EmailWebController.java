package com.mplescano.webapp.emailweb.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;
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
        return processSend(mail, mailSender);
    }
    
	private static String processSend(MailBean mail, JavaMailSender mailSender) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        if (StringUtils.hasText(mail.getFrom())) {
            helper.setFrom(mail.getFrom());
        }
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        if (mail.getPriority() != null) {
            helper.setPriority(mail.getPriority().intValue());        	
        }
        
        if (mail.getReplyTo() != null) {
            helper.setReplyTo(mail.getReplyTo());        	
        }
        
        helper.setText(mail.getBody(), false);
        
        mailSender.send(message);
        
        return "ok";
	}
	
}
