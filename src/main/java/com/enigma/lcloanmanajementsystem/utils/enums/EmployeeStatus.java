package com.enigma.lcloanmanajementsystem.utils.enums;

public enum EmployeeStatus {
    EMPLOYED,
    SELF_EMPLOYED,
    CONTRACT,
    UNEMPLOYED;

    public static boolean isValid(String value){
        if (value.isBlank()){
            return false;
        }
        for (EmployeeStatus status : EmployeeStatus.values()){
            if (status.name().equals(value)){
                return true;
            }
        }
        return false;
    }
}
