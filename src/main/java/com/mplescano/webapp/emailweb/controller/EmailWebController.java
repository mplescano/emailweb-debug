package com.mplescano.webapp.emailweb.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("mailSender")
    private JavaMailSender mailSender;
    
    /*@Autowired
    @Qualifier("mailSenderCloud")
    private JavaMailSender mailSenderCloud;
    
    @Autowired
    @Qualifier("mailSenderCloudUat")
    private JavaMailSender mailSenderCloudUat;*/
    
    @PostMapping("/send")
    public String sendEmail(/*@Valid*/ @RequestBody MailBean mail) throws Exception {
        return processSend(mail, mailSender);
    }
    
    /*@PostMapping("/send-cloud")
    public String sendEmailCloud(@Valid @RequestBody MailBean mail) throws Exception {
        return processSend(mail, mailSenderCloud);
    }
    
    @PostMapping("/send-cloud-uat")
    public String sendEmailCloudUat(@Valid @RequestBody MailBean mail) throws Exception {
        return processSend(mail, mailSenderCloudUat);
    }*/

	private static String processSend(MailBean mail, JavaMailSender mailSender) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(mail.getFrom());
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
