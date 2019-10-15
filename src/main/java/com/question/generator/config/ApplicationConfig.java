package com.question.generator.config;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(/* basePackageClasses = Application.class */basePackages = {
		"com.task.tracker.services", "com.task.tracker.DAO.impl", "com.interview.questions.db.dao" , "com.interview.questions"}, excludeFilters = @Filter({
		Controller.class, Configuration.class }))
class ApplicationConfig {

	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocation(new ClassPathResource("/config.properties"));
		return ppc;
	}
	
	@Value("${mail.smtp.host}")
	private String smtpHost;
	
	@Value("${mail.smtp.socketFactory.port}")
	private String smtpSocketFactoryPort;	
	
	@Value("${mail.smtp.socketFactory.class}")
	private String smtpSocketFactoryClass;
	
	@Value("${mail.smtp.auth}")
	private String smtpAuth;
	
	@Value("${mail.smtp.port}")
	private String smtpPort;
	
	@Value("${mail.username}")
	private String username;
	
	@Value("${mail.password}")
	private String password;
	
	@Value("${mail.subject}")
	private String subject;
	
	@Bean
	public Message createMailMessage() throws AddressException, MessagingException
	{
	Properties props = new Properties();
	props.put("mail.smtp.host", smtpHost);
	props.put("mail.smtp.socketFactory.port", smtpSocketFactoryPort);
	props.put("mail.smtp.socketFactory.class", smtpSocketFactoryClass);
	props.put("mail.smtp.auth", smtpAuth);
	props.put("mail.smtp.port", smtpPort);
	Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	});

		Message message = new MimeMessage(session);
		message.setSubject(subject);
		message.setFrom(new InternetAddress(username));
		
	return message;
	
	
	}
}