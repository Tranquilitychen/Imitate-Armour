package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");

		
		//发送邮件账号的密码
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("shenzhuhaoneusoft", "a123456");
			}
		};

		Session session = Session.getInstance(props, auth);

		
		Message message = new MimeMessage(session);

		//发送邮件的账号
		message.setFrom(new InternetAddress("shenzhuhaoneusoft@163.com"));

		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 

		//邮件主题
		message.setSubject("注册激活账号");
		

		message.setContent(emailMsg, "text/html;charset=utf-8");



		Transport.send(message);
	}
}
