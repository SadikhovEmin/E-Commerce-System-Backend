package com.example.ECommerceSystemBackend.Model;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    private SystemEmailAccount sender;
    private String host;
    private String port;
    private Properties properties;
    private Session session;
    public Email(SystemEmailAccount sender,String host,String port){
        this.sender = sender;
        this.host = host;
        this.port = port;
        properties = System.getProperties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host",this.host);
        properties.put("mail.smtp.port",this.port);
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.ssl.trust",this.host);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
    }

    public String SendAccountVerificationMessage(String email){
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender.getEmailAddress(),sender.getEmailPassword());
            }
        });
        session.setDebug(true);
        try{
            Random rnd = new Random();
            int number = rnd.nextInt(999999);
            String rnd_num =  String.format("%06d", number);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender.getEmailAddress()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Welcome to the E-Commerce System");
            message.setText(String.format("Hello,\nYou registered an account on E-commerce, before being able to use your account you need verify that this is your email adress by writing this code to textbox: %s\nKind Regards, E-commerce",rnd_num));
            Transport.send(message);
            return rnd_num;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return null;
    }
}
