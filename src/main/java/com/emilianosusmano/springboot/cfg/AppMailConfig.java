package com.emilianosusmano.springboot.cfg;

import java.io.IOException;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;



@Configuration
@PropertySources({ @PropertySource("classpath:/cfg/email_cfg.properties"), @PropertySource("classpath:/cfg/email_templates.properties") })
@SuppressWarnings("deprecation")
public class AppMailConfig {
	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private Integer port;
	@Value("${spring.mail.protocol}")
	private String mailTransportProtocol;
	@Value("${velocity.template.location}")
	private String velocityTemplateLocation;
	@Value("${spring.mail.username}")
	private String mailUser;
	@Value("${spring.mail.password}")
	private String mailPassword;

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		Session session = Session.getInstance(getMailProperties(), new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailUser, mailPassword);
			}
		});
		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		javaMailSender.setSession(session);
		return javaMailSender;
	}

	@Bean
	public VelocityEngine getVelocityEngine() throws VelocityException, IOException {
		VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();
		velocityEngineFactoryBean.setResourceLoaderPath(velocityTemplateLocation);
		return velocityEngineFactoryBean.createVelocityEngine();
	}

	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", mailTransportProtocol);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("smail.smtp.starttls.required", "true");
		properties.setProperty("mail.smtp.quitwait", "true");
		properties.setProperty("mail.debug", "false");
		return properties;
	}
}