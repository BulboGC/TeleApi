package com.desertgm.app.Enums.Lead;

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
}
