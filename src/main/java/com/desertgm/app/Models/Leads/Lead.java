package com.desertgm.app.Models.Leads;

import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document("Lead")
public class Lead {

    @Indexed
    @Id
    private String id;

    private Long Porte;
    private Long identificadorMatrizFilial;
    @Indexed
    private Long CNPJ;
    private String razaoSocial;
    private String clientName;

    private String Email;

    private String Phone1;

    private String Phone2;

    @Indexed
    private Long CNAE;



    /*PENDING,CONFIRMED,SUSPENDED,REFUSED */
    private int status;

    private Date dateForCall;

    private String comments;

    @Indexed
    private String userId;

    public Lead() {
        this.userId = "";
    }

    public Lead(Long identificadorMatrizFilial, Long CNPJ, String razaoSocial, String clientName, String email, String phone1, String phone2, Long CNAE, int status, Date dateForCall, String comments, String userId) {
        this.identificadorMatrizFilial = identificadorMatrizFilial;
        this.CNPJ = CNPJ;
        this.razaoSocial = razaoSocial;
        this.clientName = clientName;
        Email = email;
        Phone1 = phone1;
        Phone2 = phone2;
        this.CNAE = CNAE;

        this.status = status;
        this.dateForCall = dateForCall;
        this.comments = comments;
        this.userId = "";

    }



}
