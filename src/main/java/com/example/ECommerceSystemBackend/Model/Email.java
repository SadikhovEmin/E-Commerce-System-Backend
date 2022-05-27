package com.example.ECommerceSystemBackend.Model;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.PreencodedMimeBodyPart;

public class Email {
    private SystemEmailAccount sender;
    private String host;
    private String port;
    private Properties properties;
    private Session session;

    public Email() {
    }

    public Email(SystemEmailAccount sender, String host, String port) {
        this.sender = sender;
        this.host = host;
        this.port = port;
        properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", this.host);
        properties.put("mail.smtp.port", this.port);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.trust", this.host);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
    }

    public String SendAccountVerificationCode(String email) {
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender.getEmailAddress(), sender.getEmailPassword());
            }
        });
        session.setDebug(true);
        try {
            String code = generateRandomCode(6);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender.getEmailAddress()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Welcome to the E-Commerce System");
            message.setText(String.format(
                    "Hello,\nYou registered an account on E-commerce, before being able to use your account you need verify that this is your email adress by writing this code to textbox: %s\nKind Regards, E-commerce",
                    code));
            Transport.send(message);
            return code;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return null;
    }

    public String SendAuthenticationCode(String email, String authenticationQRCodeBase64) {
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender.getEmailAddress(), sender.getEmailPassword());
            }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender.getEmailAddress()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("E-Commerce System 2-Factor Authentication");
            String body = authenticationQRCodeBase64.replace("data:image/png;base64", "");
            MimeBodyPart filePart = new PreencodedMimeBodyPart("base64");
            filePart.setFileName("qrcode.png");
            filePart.setHeader("Content-ID", "<qrcode>");
            filePart.setText(body);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(filePart);
            message.setContent(multipart);
            Transport.send(message);
            return authenticationQRCodeBase64;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return null;
    }
    
    public void SendStatusOfStoreToOwner(Store store, StoreOwner owner) {
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender.getEmailAddress(), sender.getEmailPassword());
            }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender.getEmailAddress()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(owner.getEmail()));
            message.setSubject("Status of Your Store");
            String status = store.getSuspended() ? "suspended" : "not suspended";
            String txt = "Hello" + owner.getName() + ",\nYour store is " + status;
            message.setText(txt);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    
    public void SendCheckoutMessageToCustomer(StoreOwner owner, Customer customer) {
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender.getEmailAddress(), sender.getEmailPassword());
            }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender.getEmailAddress()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(customer.getEmail()));
            message.setSubject("Checkout is successfull");
            String txt = "Hello " + customer.getName() + ",\nYour checkout is successfull and payment made to "+ owner.getWalletAddress();
            message.setText(txt);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public void SendStatusOfOrderToCustomer(CustomerOrder order) {
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender.getEmailAddress(), sender.getEmailPassword());
            }
        });
        session.setDebug(true);
        try {
            Customer customer = order.getCustomer();
            Store store = order.getStore();
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender.getEmailAddress()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(customer.getEmail()));
            message.setSubject("Checkout is successfull");
            String txt = "Hello " + customer.getName() + ",\nStatus of your order from"+store.getName()+"with Id: "+order.getId()+"is " + order.getStatus();
            message.setText(txt);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public void SendCampaignMessageToCustomers(Store store, Integer discountPercentage, List<Customer> customers) {
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender.getEmailAddress(), sender.getEmailPassword());
            }
        });
        session.setDebug(true);
        for(Customer customer : customers) {
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(sender.getEmailAddress()));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(customer.getEmail()));
                message.setSubject("New Campaign!");
                String txt = "Hello " + customer.getName() + ",\n" + store.name + " started a campaign and discounts %" + discountPercentage + " for all of its products!" ;
                message.setText(txt);
                Transport.send(message);
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
    }

    private String generateRandomCode(int digit) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digit; i++) {
            sb.append(rnd.nextInt(0, 10));
        }
        return sb.toString();
    }

}
