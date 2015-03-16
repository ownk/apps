package com.developer.core.mail;
// CVS $Id: Mail.java,v 1.2 2011-04-17 04:45:51 jc Exp $


import java.net.MalformedURLException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.developer.core.utils.SimpleLogger;


public class Mail {
	
	/*
	 	telnet mail.gmx.com 25
		dWRpc3Rlc2lzQGdteC5jb20=
		dGVzaXMyMDEx
		MAIL FROM: udistesis@gmx.com
		RCPT TO: jerezjcv@gmail.com
		DATA
		Subject: El tema del correo
		Text: esta es una prueba de correo con texto desde telnet usando servidor gmx!
		.
		QUIT
	 */
	 


	private static String 	SMTP = "smtp.gmail.com";
	private static String 	USER = "udistesis@gmail.com";
	private static String 	PASS = "tesis2011";
	private static Boolean 	SSL = true;
	private static String 	FROM = "udistesis@gmail.com";
	private static String 	FROMNAME = "SGPG-UD";
	private static Boolean	TLS = true;
	private static String	MAILDEUG ="udistesis@gmail.com";
	private static int	SMTP_PORT = 25; //puerto estandar
	
	
	public static void configure(String smtp, int smtpPort, String user, String pass, Boolean ssl, Boolean tls, String from, String fromname,
			String maildebug) {
		SMTP = smtp;
		SMTP_PORT = smtpPort;
		USER = user;
		PASS = pass;
		SSL = ssl;
		TLS = tls;
		FROM = from;
		FROMNAME = fromname;
		MAILDEUG = maildebug;
		
		
	}

	public static void enviar(String to, String toName, String subject, String urlContenido) throws EmailException, MalformedURLException {
		
		HtmlEmail correo = new HtmlEmail();
		correo.setSmtpPort(SMTP_PORT);
		correo.setSSL(SSL);
		correo.setTLS(TLS); 
		
		// Autentica al usuario en el SMTP
		if (org.apache.commons.lang.StringUtils.isNotBlank(USER) && org.apache.commons.lang.StringUtils.isNotBlank(PASS)) {
			DefaultAuthenticator da = new DefaultAuthenticator(USER, PASS);
			correo.setAuthenticator(da);
		}
		
		correo.setHostName(SMTP);
		correo.setFrom(FROM, FROMNAME);
		correo.addTo(to, toName);
		correo.setSubject(subject);

		String cuerpoMail = urlContenido;;
		correo.setHtmlMsg(cuerpoMail);

		// Envío del correo
		SimpleLogger.info("to:"+to+" toName:"+toName+" "+correo.send());
		
		
	}



}
