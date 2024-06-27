package com.desertgm.app.Models.ImportModels;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Data
@Document("Company")
public class Company {
    //https://dadosabertos.rfb.gov.br/CNPJ/Empresas0.zip

    @Id
    private String id;
    @Indexed
    private String cnpjBase;

    private String razaoSocial;

    private Long naturezaLegal;

    private Long qualificacaoResponsavelModel;

    private String capitalSocial;

    private Long porteEmpresa;

    private String enteFederativoResponsavel;
}
