package com.desertgm.app.Models.Email;

import com.desertgm.app.Enums.Email.EmailStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.time.LocalDateTime;

@Data
@Document(collection = "Email")

public class Email {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id

    private String emailId;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;
    private EmailStatus statusEmail;

}


