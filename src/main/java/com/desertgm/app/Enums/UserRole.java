package com.desertgm.app.Enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN(1),
    USER(2);
    private int roleValue;
    UserRole(int roleValue){
        this.roleValue = roleValue;
    }
}
