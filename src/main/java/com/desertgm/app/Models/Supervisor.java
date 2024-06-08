package com.desertgm.app.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Supervisor")
public class Supervisor {

    @Id
    private String id;

    private String nome;

    private String email;

    private String phone;

    private String status;

    private List<String> userId;
}
