package eu.modaclouds.sla.notification;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSender {
	private static Logger logger = LoggerFactory.getLogger(MailSender.class);
	
	private String smtpUrl;
	private String user;
	private String passwd;
	private InternetAddress from;
	private final Properties props;
	
	/**
	 * Sends an email from a gmail account.
	 * 
	 * @param smtpAddress address of smtp in the form of host:port (ex: smtp.gmail.com:587) 
	 * @param from email
	 * @param user login user
	 * @param passwd login pwd
	 */
	public MailSender(String smtpAddress, String from, String user, String passwd) {
		String host;
		String port;
		try {
			this.smtpUrl = smtpAddress;
			
			String aux[] = smtpAddress.split(":");
			if (aux.length != 2) {
				throw new MailSenderException(this.smtpUrl + " is not a valid SMTP URL");
			}
			host = aux[0];
			port = aux[1];
			
			this.from = new InternetAddress(from);
			this.user = user;
			this.passwd = passwd;
			
		} catch (AddressException e) {
			
			throw new MailSenderException(e.getMessage(), e);
		}
		props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
	}
	
	public void send(String to, String subject, String body) {
		logger.info("mail.send({}, subject", to, subject);

		Session session = Session.getInstance(props);
		
		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(from);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);
			
			Transport.send(message, user, passwd);
		} catch (AddressException e) {
			
			throw new MailSenderException(e.getMessage(), e);
			
		} catch (MessagingException e) {
			
			throw new MailSenderException(e.getMessage(), e);
		}
	}
	
	public static class MailSenderException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public MailSenderException(String msg) {
			super(msg);
		}
		
		public MailSenderException(String msg, Throwable cause) {
			super(msg, cause);
		}
	}
}
