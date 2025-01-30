package com.hms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmailSendingServiceImple implements EmailSendingService {

	
	    @Autowired
	    private JavaMailSender emailSender;

	    public void sendSimpleMessage(String to, String subject, String text) {

	        SimpleMailMessage message = new SimpleMailMessage(); 
	        
	        message.setFrom("omchaudhari@omempire.com");
	        message.setTo(to); 
	        message.setSubject(subject); 
	        message.setText(text);
	        
	        emailSender.send(message);
	    }
	
}
