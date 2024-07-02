package com.desertgm.app.DTO.Imports;

import com.desertgm.app.Enums.imports.estabelecimento.MatrizFilialEnum;
import com.desertgm.app.Enums.imports.estabelecimento.SituacaoCadastralEnum;

import java.util.Date;

public class ResponseEstabelecimentoDto {
    private String cnpj;
    private MatrizFilialEnum matrizFilial;

    private String nome_fantasia;
    private SituacaoCadastralEnum situacaoCadastral;

    //criar mascara para date
    private String dtSituacaoCadastral;

    //Pegar do banco
    private String motivoSituacaoEspecial;

    private String nome_cidade_exterior;

    //Pegar do banco
    private String pais;

    //criar mascara do Date
    private String dt_inicioAtividade;
    //Pegar do banco
    private int cnae_code;
    //Pegar do banco
    private String cnae_descricao;

    private String cnae_secundaria;
    private String tipo_Logradouro;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String uf;

    //Pegar do banco
    private String municipio;

    private String ddd1;
    private String telefone1;
    private String ddd2;
    private String telefone2;
    private String ddd_fax;
    private String fax;
    private String email;
    private String situacao_especial;
    //fazer mascara de Date
    private String dt_situacao_especial;


    ResponseEstabelecimentoDto(){

    }

}
