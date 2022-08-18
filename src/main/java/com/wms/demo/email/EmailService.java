package com.wms.demo.email;

import java.io.File;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService 
{
    @Autowired
    private JavaMailSender mailSender;
     

 
   
    public void sendMail(String to, String subject, String body) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setBcc("harikaketavarapu@gmail.com");
        message.setCc("bsdr87@gmail.com");
        message.setSubject(subject);
        message.setText(body);
        
        mailSender.send(message);
    }
   
    public void sendPreConfiguredMail(String message) 
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText(message);
        mailMessage.setTo("aloksuman007@gmail.com");
        mailSender.send(mailMessage);
    }
    
    
    
    
    public void sendMailWithAttachment(String to, String subject, String body, String fileToAttach) 
    {
    	MimeMessagePreparator preparator = new MimeMessagePreparator() 
    	{
            public void prepare(MimeMessage mimeMessage) throws Exception 
            {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setFrom(new InternetAddress("harikaketavarapu@gmail.com"));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(body);
                
             
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                
                FileSystemResource file = new FileSystemResource(new File(fileToAttach));
                helper.addAttachment("logo.txt", file);
            }
        };
        
        try {
            mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
    
    public void sendMailWithInlineResources(String to, String subject, String fileToAttach) 
    {
    	MimeMessagePreparator preparator = new MimeMessagePreparator() 
    	{
            public void prepare(MimeMessage mimeMessage) throws Exception 
            {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setFrom(new InternetAddress("harikaketavarapu@gmail.com"));
                mimeMessage.setSubject(subject);
                
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                
                helper.setText("<html><body><img src='cid:identifier1234'>   <br> <h>Hello Welcome</h1></body></html>", true);
                
                FileSystemResource res = new FileSystemResource(new File(fileToAttach));
                helper.addInline("identifier1234", res);
            }
        };
        
        try {
            mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}
