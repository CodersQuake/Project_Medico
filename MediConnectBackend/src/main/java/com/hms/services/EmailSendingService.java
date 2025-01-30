package com.hms.services;

public interface EmailSendingService {
	
	void sendSimpleMessage(String to, String subject, String message);

}
