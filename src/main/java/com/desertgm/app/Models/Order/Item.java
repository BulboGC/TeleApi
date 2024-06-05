package com.desertgm.app.Models.Order;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.Year;
@Data
public class Item {
    private String brand;
    private String model;
    private String color;
    private Year year;
    private String version;
    //data limite
    private LocalDateTime dueDate;
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
