//package com.codeup.halfguard.services;
//
//import com.codeup.halfguard.models.Post;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service("mailService")
//public class EmailService {
//    @Autowired
//    public JavaMailSender emailSender;
//
//    @Value("${spring.mail.from}")
//    private String from;
//
//    public void prepareAndSend(Post post, String subject, String body){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
////        message.setTo(post.getUser().getEmail());
//        message.setTo(post.getUser().getUsername());
//        message.setSubject(subject);
//        message.setText(body);
//
//        try{
//            this.emailSender.send(message);
//        }
//        catch (MailException exception){
//            System.err.println(exception.getMessage());
//        }
//
////        System.out.println(emailSender);
//    }
//}
