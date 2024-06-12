package com.desertgm.app.Services;

import com.desertgm.app.Enums.Email.EmailStatus;
import com.desertgm.app.Models.Email.Email;
import com.desertgm.app.Models.User.RecoveryToken;
import com.desertgm.app.Models.User.User;
import com.desertgm.app.Repositories.EmailRepository;
import com.desertgm.app.Repositories.UserRepository;
import com.desertgm.app.Services.User.RecoveryTokenService;
import com.desertgm.app.Services.User.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RecoveryTokenService recoveryTokenService;

    @Autowired
    private UserService userService;

    @Transactional
    @Async
    public void sendEmail(@NotNull Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            mailSender.send(message);
            email.setStatusEmail(EmailStatus.SENT);
        } catch (MailException e) {
            email.setStatusEmail(EmailStatus.ERROR);
            e.printStackTrace(); // Adicionado para logar o erro
        } finally {
            emailRepository.save(email);
        }
    }

    public Email sendRecoveryPass(String emailTo) {
        // Criar e salvar o token no banco de dados
        RecoveryToken recoveryToken = new RecoveryToken();
        recoveryToken.setCreateAt(LocalDateTime.now());
        User user = userService.getUserByEmail(emailTo);
        recoveryToken.setUserId(user.getId());
        recoveryToken = recoveryTokenService.addRecoveryToken(recoveryToken);

        // Usar o ID gerado pelo MongoDB como token
        String token = recoveryToken.getId();

        // Criar e enviar o e-mail de recuperação
        Email email = new Email();
        email.setEmailTo(emailTo);
        email.setEmailFrom("seu-email@gmail.com");
        email.setSubject("Recovery Password");
        email.setText("Token para a recuperação de senha: " + token +
                "\nLink para a recuperação de conta: " +
                "http://localhost:5173/recovery/" );
        sendEmail(email); // Enviar o e-mail assíncronamente

        return email;
    }
}
