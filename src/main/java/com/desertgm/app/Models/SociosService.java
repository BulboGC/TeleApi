package com.desertgm.app.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("Socio")
public class SociosService {


    private Long cnpjBase;

    private int identificador;

    private String nome_razao;

    private String cpf_cnpj_socio;

    private Long qualificacao;

    private Date data_entrada_socidedade;

    private Long pais;

    private String representante_legal;

    private String nome_representante;

    private Long qualificacao_representante;

    private int faixa_etaria;





    /*1 para os intervalos entre 0 a 12 anos;
- 2 para os intervalos entre 13 a 20 anos;
- 3 para os intervalos entre 21 a 30 anos;
- 4 para os intervalos entre 31 a 40 anos;
- 5 para os intervalos entre 41 a 50 anos;
- 6 para os intervalos entre 51 a 60 anos;
- 7 para os intervalos entre 61 a 70 anos;
- 8 para os intervalos entre 71 a 80 anos; - 9 para maiores de 80 anos.
- 0 para não se aplica.*/

}
