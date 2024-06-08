package com.desertgm.app.Enums.Lead;

import com.desertgm.app.Enums.UserRole;
import lombok.Data;
import lombok.Getter;

@Getter
public enum LeadStatus {
    /*PENDING,CONFIRMED,SUSPENDED,REFUSED */
    PENDING(1),
    CONFIRMED(2),
    SUSPENDED(3),
    REFUSED(4);
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
