package com.interview.questions.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.interview.questions.beans.ResponseBean;
import com.interview.questions.enums.NotificationType;
import com.interview.questions.misc.NotificationMessages;
import com.task.tracker.services.MailService;

@Controller
public class SendMailController {
	
	@Autowired
	MailService mailService;

	@RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@Scope("session")
	public @ResponseBody ResponseBean sendmail(HttpSession session) {
		
		try {
			mailService.sendMail(session);
		} catch (MessagingException e) {
			
			e.printStackTrace();
			return new ResponseBean(NotificationMessages.MAIL_SENT_NOT_SUCCESSFULLY, NotificationType.ERROR, false);
		}
		session.removeAttribute("test");
		return new ResponseBean(NotificationMessages.MAIL_SENT_SUCCESSFULLY, NotificationType.INFO, true); 

//		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.abv.bg");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "465");
//		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("mih_mih_por@abv.bg", "ppapbpc");
//			}
//		});
//
//		try {
//
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress("mih_mih_por@abv.bg"));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mihail.stankov.mihaylov@gmail.com"));
//			message.setSubject("Testing Subject");
//			message.setText("Hello {name},\r\n" + 
//					"In regards to your job applciation in NewCorpAD we send you a test as follow up on the first interview. It woiuld be greatly appreciated if you could find some time to do the test in the next one week. You can find the test in the following link <a href=\"localhost:8080/my-artifactId/test/{testId}\">localhost:8080/my-artifactId/test/{testId}</a> .\r\n" + 
//					"Regards NewCorpAD HR team");
//
//			Transport.send(message);
//
//			System.out.println("Done");
//
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
	}
}
