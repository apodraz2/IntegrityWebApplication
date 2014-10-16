package model;



import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class SendEmail {
	private User user;
	List<Item> items;
	private int port = 465;
	private String host = "smtp.gmail.com";
	private String from = "apodra86@gmail.com";
	private String to;
	private boolean auth = true;
	private String username = "apodra86@gmail.com";
	private String password = "Sue22sueApod5418";
	private Protocol protocol = Protocol.SMTP;
	private boolean debug = true;
	
	public SendEmail(List<Item> items, User user) {
		this.items = items;
		this.user = user;
		this.to = user.getEmail();
	}
	
	public void send() throws AddressException, MessagingException {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.ssl.enable", true);
		System.out.println("Hello."); 
		
		Authenticator authenticator = null;
		if (auth) {
			props.put("mail.smtp.auth", true);
			authenticator = new Authenticator() {
				private PasswordAuthentication pa = new PasswordAuthentication(username, password);
				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return pa;
				}
			};
		}
		
		Session session = Session.getInstance(props, authenticator);
		session.setDebug(debug);
		MimeMessage message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(from));
		InternetAddress[] address = {new InternetAddress(to)};
		message.setRecipients(Message.RecipientType.TO, address);
		message.setSubject("New Items");
		message.setSentDate(new Date());
		
		Multipart multipart = new MimeMultipart("alternative");
		
		MimeBodyPart textPart = new MimeBodyPart();
		
		Item item = items.get(0);
		String textContent = item.getContent() + "\n";
		textPart.setText(textContent);
		
		MimeBodyPart htmlPart = new MimeBodyPart();
		String HTMLContent = "<html><h1>" + item.getContent() + 
				"<h2>" + item.getCondition() + "</h2>" +
				//"<h2>" + "Price: $" + item.getPrice().getValue() + "</h2>" +
				"</h1>" + "<p>" + item.getURL() + "</p>" + 
				"<p>This is an automatically generated email</p></html>\n";
		htmlPart.setContent(HTMLContent, "text/html");
		
		multipart.addBodyPart(textPart);
		multipart.addBodyPart(htmlPart);
		message.setContent(multipart);
		
		Transport.send(message);
	}
	
	public enum Protocol {
		SMTP,
		SMTPS,
		TLS
	}
}
