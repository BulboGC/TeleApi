package com.desertgm.app.Services;

import com.desertgm.app.Enums.Email.EmailStatus;
import com.desertgm.app.Repositories.EmailRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    MailSender mailSender;


    @Transactional
    public com.desertgm.app.Models.Email.Email sendEmail(@NotNull com.desertgm.app.Models.Email.Email email){

        email.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            mailSender.send(message);
            email.setStatusEmail(EmailStatus.SENT);
        }catch (MailException e){
            email.setStatusEmail(EmailStatus.ERROR);
        }finally {
            return email.save(email);
        }

    }
}
