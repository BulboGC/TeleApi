package com.desertgm.app.DTO.Lead;

import java.time.LocalDateTime;
import java.util.Date;

public record LeadDto(
        Long identificadorMatrizFilial,
        Long CNPJ,
        String razaoSocial,
        String clientName,

        String Email,

         String Phone1,

         String Phone2,

         Long CNAE,

         String activity,

        /*PENDING,CONFIRMED,SUSPENDED,REFUSED */
         int status,

         Date dateForCall,

         String comments,

         String userId) {
}
