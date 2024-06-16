package com.desertgm.app.Models.Order;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Date;

@Data
public class Item {


    private String brand;
    private String model;
    private String color;
    private String year;
    private String version;
    //data limite
    private Date dueDate;
    private Long amount;
    /*AVISTA CONSORCIO FINANCIAMENTO*/
    private String payment;
    private String purchaseReason;
    /*limite maximo do cliente*/
    private String maxPayment;
    /*SIM NÂO INDEFINIDO*/
    private String tradeInValue;
    /*VAREJO ATACADO*/
    private String orderType;

}
