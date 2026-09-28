package com.enigma.lcloanmanajementsystem.utils.enums;

public enum LoanStatus {
    PENDING,
    APPROVED,
    REJECTED;

    public static boolean isValid(String value){
        if (value.isBlank()){
            return false;
        }
        for (LoanStatus loan : LoanStatus.values()){
            if (loan.name().equals(value)){
                return true;
            }
        }
        return false;
    }
}
