package com.desertgm.app.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document("Estabelecimento")
public class EstabelecimentoModel {

    //https://dadosabertos.rfb.gov.br/CNPJ/Estabelecimentos0.zip
    private Long id;
    @Id
    private Long cnpjFull;


    private Long cnpjBaseId;

    private String cnpjOrdem;
    private String cnpjDV;


    private Long identificadorMatrizFilialId; // Alterado para Long

    private String nomeFantasia;


    private Long situtacaoCadastralId; // Alterado para Long

    private Date dataSituacaoCadastral;


    private Long motivoSituacaoCadastralId; // Alterado para Long

    private String nomeCidadeExterior;


    private Long paisId; // Alterado para Long

    private Date dataInicioAtividade;


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
    private Date datasituacaoespecial;
}
