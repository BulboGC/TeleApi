package com.desertgm.app.Models.ImportModels.Estabelecimento;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Municipio {
    private int code;
    private String description;

    
}
