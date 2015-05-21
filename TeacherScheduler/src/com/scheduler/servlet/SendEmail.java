package com.scheduler.servlet;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail
{
	private static final String HOST = "smtp.gmail.com";
	 private static final int PORT = 465;
	 // Adres email osby która wysy³a maila
	 private static final String FROM = "komunikator.no.reply@gmail.com";
	 // Has³o do konta osoby która wysy³a maila
	 private static final String PASSWORD = "Komunikator";
	
	 /*public static void main(String[] args) {
	  try {
	   new SendEmail().send("mm43428@st.amu.edu.pl","[KOM]Hello World","To mój pierwszy mail wys³any za pomoc¹ JavaMailAPI.");
	  } catch (MessagingException e) {
	   e.printStackTrace();
	  }
	 }*/

	 public void send(String TO,String SUBJECT,String CONTENT) throws MessagingException {
		 
	  Properties props = new Properties();
	  props.put("mail.transport.protocol", "smtps");
	  props.put("mail.smtps.auth", "true");

	  // Inicjalizacja sesji
	  Session mailSession = Session.getDefaultInstance(props);

	  // Tworzenie wiadomoœci email
	  MimeMessage message = new MimeMessage(mailSession);
	  message.setSubject(SUBJECT);
	  message.setContent(CONTENT, "text/plain; charset=ISO-8859-2");
	  message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));

	  Transport transport = mailSession.getTransport();
	  transport.connect(HOST, PORT, FROM, PASSWORD);

	  // wys³anie wiadomoœci
	  transport.sendMessage(message, message
	    .getRecipients(Message.RecipientType.TO));
	  transport.close();
	 }
}