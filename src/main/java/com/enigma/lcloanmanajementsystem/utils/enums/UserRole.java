package com.enigma.lcloanmanajementsystem.utils.enums;

public enum UserRole {
    CUSTOMER,
    ADMIN;

    public static boolean isValid(String value){
        if (value.isBlank()){
            return false;
        }
        for (UserRole role : UserRole.values()){
            if (role.name().equals(value)){
                return true;
            }
        }
        return false;
    }
}
