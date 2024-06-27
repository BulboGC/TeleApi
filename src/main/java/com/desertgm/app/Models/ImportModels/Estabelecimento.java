package com.desertgm.app.Models.ImportModels;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
@Data
@Document("Estabelecimento")
public class Estabelecimento {

    //https://dadosabertos.rfb.gov.br/CNPJ/Estabelecimentos0.zip
    @Indexed
    private Long cnpjFull;
    private String cnpjBaseId;

    private String cnpjOrdem;
    private String cnpjDV;


    private Long identificadorMatrizFilialId; // Alterado para Long

    private String nomeFantasia;


    private Long situtacaoCadastralId; // Alterado para Long

    private String dataSituacaoCadastral;


    private Long motivoSituacaoCadastralId; // Alterado para Long

    private String nomeCidadeExterior;


    private Long paisId; // Alterado para Long

    private String dataInicioAtividade;

    @Indexed
    @Field(name = "cnaeFiscalPrincialId")
    private Long cnaeFiscalPrincipalId; // Alterado para Long


    private String cnaeFiscalSecundaria;
    private String tipoLogradouro;
    private String logradouro;

    private String numero;


    private String complemento;



    private String bairro;
    private String cep;
    private String uf;


    private Long municipioId; // Alterado para Long


    private String ddd1;
    private String telefone1;
    private String ddd2;
    private String telefone2;
    private String dddfax;
    private String fax;
    private String email;
    private String situacaoespecial;
    private String datasituacaoespecial;



}
