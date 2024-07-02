package com.desertgm.app.Enums;

import lombok.Getter;

@Getter
public enum UserRole {
    USER(1),
    ADMIN(2),
    SUPERVISOR(3),
    SELLER(4);

    private int roleValue;

    UserRole(int roleValue){
        this.roleValue = roleValue;
    }
    public static UserRole fromValue(int value){
        for (UserRole role : UserRole.values()) {
            if (role.getRoleValue() == value) {
                return role;
            }
        }
        throw new RuntimeException("Role invalida");
    }
}
