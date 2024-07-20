package com.desertgm.app.Models.ImportModels.Estabelecimento;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document("Estabelecimento")
public class Estabelecimento {


    @Indexed
    private String cnpjBase;
    @Indexed

    private String cnpjOrdem;
    private String cnpjDV;


    private int MatrizFilial; // Alterado para Long

    private String nomeFantasia;


    private int situtacaoCadastral; // Alterado para Long

    private Date dt_SituacaoCadastral;


    private int motivoSituacaoCadastral; // Alterado para Long

    private String nomeCidadeExterior;


    private int pais; // Alterado para Long

    private Date dt_inicioAtividade;

    @Indexed
    private int cnae;


    private String cnae_secundaria;
    private String tipo_Logradouro;
    private String logradouro;

    private String numero;


    private String complemento;



    private String bairro;
    private String cep;
    private String uf;


    private int municipio; // Alterado para Long


    private String ddd1;
    private String telefone1;
    private String ddd2;
    private String telefone2;
    private String ddd_fax;
    private String fax;
    private String email;
    private String situacao_especial;
    private Date dt_situacao_especial;



}
