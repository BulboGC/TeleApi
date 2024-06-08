package com.desertgm.app.DTO;

import lombok.Data;

import java.util.HashMap;
@Data
public class ResponseDto {

    private HashMap<String,String> response;



    public ResponseDto(){
        this.response = new HashMap<>();
    }
    public void addResponse(String key,String valor){
        response.put(key,valor);
    }
}
