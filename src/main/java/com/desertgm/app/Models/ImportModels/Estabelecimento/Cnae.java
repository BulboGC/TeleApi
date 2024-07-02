    package com.desertgm.app.Models.ImportModels.Estabelecimento;

    import lombok.Data;
    import org.springframework.data.mongodb.core.mapping.Document;
    import org.springframework.data.mongodb.core.mapping.Field;

    @Data
    @Document(collection = "Cnae")
    public class Cnae {


        private int code;
        private String descricao;

    }
