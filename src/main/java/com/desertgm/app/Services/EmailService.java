package com.desertgm.app.Services;

import com.desertgm.app.Enums.EmailStatus;
import com.desertgm.app.Models.EmailModel;
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
    public EmailModel sendEmail(@NotNull EmailModel emailModel){

        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            mailSender.send(message);
            emailModel.setStatusEmail(EmailStatus.SENT);
        }catch (MailException e){
            emailModel.setStatusEmail(EmailStatus.ERROR);
        }finally {
            return emailRepository.save(emailModel);
        }

    }
}
