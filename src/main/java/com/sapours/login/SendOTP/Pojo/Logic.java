package com.sapours.login.SendOTP.Pojo;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Logic {
	

	public void sendEmail(String message, String subject, String to, String from) {

		Properties properties = System.getProperties();
		System.out.println("PROPERTIES " + properties);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1: to get the session object..
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub

				return new PasswordAuthentication("Vinayakhouse123@gmail.com", "gnnkajgoiulfhjsj");

			}

		});

		// step 2: compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);

		try {
			// from email
			m.setFrom(from);

			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message
			m.setText(message);

			// send

			// Step 3: send the message using Transport class
			Transport.send(m);

			System.out.println("Sent success...........");
			System.out.println("mail send to " + to);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
