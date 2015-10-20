package eu.modaclouds.sla.notification;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.modaclouds.sla.notification.MailSender.MailSenderException;

public class MailSenderTest {

	@Test
	public void testSend() {
		try {
			String user = System.getenv("MAIL_USER");
			String passwd = System.getenv("MAIL_PWD");
			String to = System.getenv("MAIL_TO");
			
			if (user == null || passwd == null) {
				return;
			}
			
			MailSender sender = new MailSender("smtp.gmail.com", "rosogonatos@gmail.com", user, passwd);
			sender.send(to, "Mail sender test", "Body intentionally left blank\n\n\\0");
		} catch (MailSenderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

}
