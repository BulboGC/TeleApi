package com.desertgm.app.Models.Leads;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Data
@Document("Company")
public class Company {
    //https://dadosabertos.rfb.gov.br/CNPJ/Empresas0.zip
    @MongoId
    private Long id;

    private Long cnpjBaseInt;

    private String cnpjBaseStr;

    private String razaoSocial;

    private Long naturezaLegal;

    private Long qualificacaoResponsavelModel;

    private double capitalSocial;

    private Long porteEmpresa;

    private String enteFederativoResponsavel;
}
