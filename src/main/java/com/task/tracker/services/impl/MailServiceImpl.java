package com.task.tracker.services.impl;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.questions.db.entities.TestModel;
import com.task.tracker.services.MailService;

@Service
public class MailServiceImpl implements MailService{
	
	private static final String MAIL_CONTENT = "Hello name,\r\n" + 
			"\r\n" + 
			"In regards to your job applciation in NewCorpAD we send you a test as follow up on the first interview. It woiuld be greatly appreciated if you could find some time to do the test in the next one week. You can find the test in the following link localhost:8080/my-artifactId/test/testId\r\n" + 
			"\r\n" + 
			"Regards NewCorpAD HR team";
	
	@Autowired
	Message message;

	@Override
	public void sendMail(HttpSession session) throws AddressException, MessagingException {
		
		TestModel testModel =  (TestModel) session.getAttribute("test");
		String text = MAIL_CONTENT;
		
		text = text.replace("name", testModel.getCandidateName());
		text = text.replace("testId", testModel.get_id().toString());
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(testModel.getCandidateMail()));
		message.setText(text);
		
		Transport.send(message);
		
	}

}
