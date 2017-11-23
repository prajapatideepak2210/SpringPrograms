package com.bridgelabz.mailUtility;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MyMailSender {
	
        
	@Autowired
	JavaMailSenderImpl mailSender;
  
	
	public boolean sendMail(String to, String message,String subject) {
		
	      try {
	    	  
	    	  MimeMessage msg = mailSender.createMimeMessage();
	    	  MimeMessageHelper helper = new MimeMessageHelper(msg,true,"UTF-8");
	    	  helper.setTo(to);
	    	  helper.setSubject(subject);
	    	  helper.setText(message);
	    	  mailSender.send(msg);
	    	  return true;
	    	  
		} catch (MessagingException e) {
			System.out.println("Hello Deepak mail not sent");
			return false;
		}
	}
}  
