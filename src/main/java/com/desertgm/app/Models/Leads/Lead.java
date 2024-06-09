package com.desertgm.app.Models.Leads;

import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDateTime;
@Data
public class Lead {
    private String Porte;
    private Long identificadorMatrizFilial;
    private Long CNPJ;
    private String razaoSocial;
    private String clientName;

    private String Email;

    private String Phone1;

    private String Phone2;

    private Long CNAE;

    private String activity;

    /*PENDING,CONFIRMED,SUSPENDED,REFUSED */
    private int status;

    private LocalDateTime dateForCall;

    private String comments;

    private String userId;
}