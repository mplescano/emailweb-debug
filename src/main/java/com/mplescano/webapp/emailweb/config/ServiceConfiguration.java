package com.mplescano.webapp.emailweb.config;

import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * @author s6026865
 * @see http://www.baeldung.com/spring-factorybean
 * @see https://stackoverflow.com/questions/36475093/spring-boot-a-servletcontext-is-required-to-configure-default-servlet-handling
 */
@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class ServiceConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "spring.mail", name = "debug", havingValue = "true", matchIfMissing = false)
    public Session mailSession(final MailProperties properties) throws Exception {
        Properties props = asProperties(properties.getProtocol(), properties.getProperties());
        props.setProperty("mail." + properties.getProtocol() + ".host", properties.getHost());
        if (properties.getPort() != null) {
            props.put("mail." + properties.getProtocol() + ".port", properties.getPort()); //TLS Port??
        }
        if (properties.getUsername() != null) {
            props.put("mail." + properties.getProtocol() + ".from", properties.getUsername());
        }
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getUsername(), properties.getPassword());
            }
        };
        Session session = Session.getInstance(props, auth);
        OutputStream os = new WrapperLogbackOutputStream(LoggerFactory.getLogger(ServiceConfiguration.class));
        PrintStream ps = new PrintStream(os);
        session.setDebug(true);
        session.setDebugOut(ps);
        return session;
    }
    
    private Properties asProperties(String protocol, Map<String, String> source) {
        Properties properties = new Properties();
        source.entrySet().stream().forEach(itemEntry -> {
            properties.setProperty(itemEntry.getKey().replace(".smtp.", "." + protocol + "."), itemEntry.getValue());
        });
        return properties;
    }

}