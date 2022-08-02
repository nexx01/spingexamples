package com.example.demo;

import javax.mail.MessagingException;

public interface EmailService {

    void SendSimpleMessage(String to, String subject, String text);


    void sendMessageWithAttachment(String to, String subject, String text,
                                   String pathToAttachment) throws MessagingException;

    void sendSimpleMessageUsingTemplate(String to,
                                        String subject,
                                        String... templateModel);
}
