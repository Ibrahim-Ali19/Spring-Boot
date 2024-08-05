package com.springMail.SpringMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String fromMail;
	
	public void sendMail(String mail , MailStructure mailStructure) {
		SimpleMailMessage simpleMail = new SimpleMailMessage();
		simpleMail.setFrom(fromMail);
		simpleMail.setSubject(mailStructure.getSubject());
		simpleMail.setText(mailStructure.getMessage());
		simpleMail.setTo(mail);
		
		mailSender.send(simpleMail);
	}
}
