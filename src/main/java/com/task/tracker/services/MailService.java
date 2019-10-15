package com.task.tracker.services;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;

public interface MailService {
	
	public void sendMail(HttpSession session) throws AddressException, MessagingException;

}
