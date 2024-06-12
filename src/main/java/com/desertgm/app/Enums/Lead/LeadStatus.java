package com.desertgm.app.Enums.Lead;

import com.desertgm.app.Enums.UserRole;
import lombok.Data;
import lombok.Getter;

@Getter
public enum LeadStatus {

    PENDING(1),//padrao
    CONFIRMED(2),//criação de pedido
    SUSPENDED(3),//data para ligar
    REFUSED(4);//recusou o produto


    private int leadStatus;
    LeadStatus(int leadstatus){
        this.leadStatus = leadstatus;
    }

    public static LeadStatus fromValue(int value){
        for (LeadStatus role : LeadStatus.values()) {
            if (role.getLeadStatus() == value) {
                return role;
            }
        }
        throw new RuntimeException("Role invalida");
    }
}
