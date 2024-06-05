package com.desertgm.app.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "Supervisor")
public class Supervisor {

    @Id
    private String id;

    private String nome;

    private String email;

    private String phone;

    private String status;

    private String userId;
}
