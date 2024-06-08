package com.desertgm.app.DTO;

import java.time.LocalDateTime;

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

         LocalDateTime dateForCall,

         String comments,

         String userId) {
}
