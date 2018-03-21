package com.mplescano.webapp.emailweb.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.OutputStream;
import java.io.PrintStream;

import javax.mail.Session;

/**
 * @author s6026865
 * @see http://www.baeldung.com/spring-factorybean
 * @see https://stackoverflow.com/questions/36475093/spring-boot-a-servletcontext-is-required-to-configure-default-servlet-handling
 */
@Configuration
public class ServiceConfiguration {

    @Bean(name = "mailSender")
    public JavaMailSender mailSender(@Qualifier("mailSession") Session session) {
    	OutputStream os = new WrapperLogbackOutputStream(LoggerFactory.getLogger(ServiceConfiguration.class));
		PrintStream ps = new PrintStream(os);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        session.setDebug(true);
        session.setDebugOut(ps);
        mailSender.setSession(session);
        return mailSender;
    }

    @Bean(name = "mailSession")
    public FactoryBean<Object> mailSessionFactoryBean() {
        JndiObjectFactoryBean mailSessionFactoryBean = new JndiObjectFactoryBean();
        mailSessionFactoryBean.setJndiName("mail/hbsbpmail");
        mailSessionFactoryBean.setResourceRef(true);
        mailSessionFactoryBean.setExpectedType(Session.class);
        return mailSessionFactoryBean;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Session mailSession() throws Exception {
        return (Session) mailSessionFactoryBean().getObject();
    }
    
    /*@Bean(name = "mailSenderCloud")
    public JavaMailSender mailSenderCloud(@Qualifier("mailSessionCloud") Session session) {
    	OutputStream os = new WrapperLogbackOutputStream(LoggerFactory.getLogger(ServiceConfiguration.class));
		PrintStream ps = new PrintStream(os);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        session.setDebug(true);
        session.setDebugOut(ps);
        mailSender.setSession(session);
        return mailSender;
    }
    
    @Bean(name = "mailSessionCloud")
    public FactoryBean<Object> mailSessionCloudFactoryBean() {
        JndiObjectFactoryBean mailSessionFactoryBean = new JndiObjectFactoryBean();
        mailSessionFactoryBean.setJndiName("mail/cloudrelaymail");
        mailSessionFactoryBean.setResourceRef(true);
        mailSessionFactoryBean.setExpectedType(Session.class);
        return mailSessionFactoryBean;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Session mailSessionCloud() throws Exception {
        return (Session) mailSessionCloudFactoryBean().getObject();
    }
    
    @Bean(name = "mailSenderCloudUat")
    public JavaMailSender mailSenderCloudUat(@Qualifier("mailSessionCloudUat") Session session) {
    	OutputStream os = new WrapperLogbackOutputStream(LoggerFactory.getLogger(ServiceConfiguration.class));
		PrintStream ps = new PrintStream(os);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        session.setDebug(true);
        session.setDebugOut(ps);
        mailSender.setSession(session);
        return mailSender;
    }
    
    @Bean(name = "mailSessionCloudUat")
    public FactoryBean<Object> mailSessionCloudUatFactoryBean() {
        JndiObjectFactoryBean mailSessionFactoryBean = new JndiObjectFactoryBean();
        mailSessionFactoryBean.setJndiName("mail/cloudrelay-uat-mail");
        mailSessionFactoryBean.setResourceRef(true);
        mailSessionFactoryBean.setExpectedType(Session.class);
        return mailSessionFactoryBean;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Session mailSessionCloudUat() throws Exception {
        return (Session) mailSessionCloudUatFactoryBean().getObject();
    }*/
}