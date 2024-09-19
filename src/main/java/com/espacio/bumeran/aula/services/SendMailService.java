package com.espacio.bumeran.aula.services;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.MessagingException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {
	
	//@Autowired
	//private JavaMailSender javaMailSender;
	
	public void sendMail (String from, String to, String subject, String body) {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.ionos.es");
		javaMailSender.setPort(587);   
		javaMailSender.setUsername("inscripciones@espaciobumeran.com");
		javaMailSender.setPassword("DavidSanchez_2019");
	    
	    Properties props = javaMailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");    
		
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		
		javaMailSender.send(simpleMailMessage);
	}
	
	public void sendFormattedEmail(String from, String to, String subject, String htmlBody) {
        // Configuración del servidor SMTP (cambia según tu proveedor de correo)
        String smtpHost = "smtp.ionos.es"; // Cambiar por el servidor SMTP que uses
        String smtpPort = "587"; // Puerto para SMTP con TLS
        String senderEmail = "inscripciones@espaciobumeran.com"; // Cambia por tu correo
        String senderPassword = "DavidSanchez_2019"; // Cambia por tu contraseña

        // Propiedades de la conexión SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Crear la sesión de correo con autenticación
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail, "Espacio Búmeran"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            

            // Cuerpo del correo en formato HTML
            
            String htmlContent = htmlBody; 
            if (htmlBody == null || htmlContent.equals("")) {
            	htmlBody = "<h2>¡Gracias por inscribirte en nuestro curso!</h2>"
                        + "<p>Para confirmar tu inscripción, por favor haz clic en el siguiente enlace:</p>"
                        + "<a href=\"https://espaciobumeran.com\">ENLACE A ESPACIO BUMERAN</a>"
                        + "<p>Si no puedes hacer clic en el enlace, copia y pega esta URL en tu navegador:</p>"
                        ;           	
            }           

            // Configurar el cuerpo como HTML
            message.setContent(htmlBody, "text/html");

            // Enviar el correo
            Transport.send(message);

            System.out.println("Correo enviado correctamente a " + to);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
        	 e.printStackTrace();
             System.out.println("Error al enviar el correo: " + e.getMessage());
		}
    }	
}	
