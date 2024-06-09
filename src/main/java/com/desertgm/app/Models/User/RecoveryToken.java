package com.desertgm.app.Models.User;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Random;

@Document("recoveryToken")

@Data
public class RecoveryToken {

    @Indexed
    @Id
    private String id;

    private String userId;

    @Indexed(expireAfterSeconds = 300)
    private LocalDateTime createAt;

    public RecoveryToken() {
    }




}
