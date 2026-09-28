package com.enigma.lcloanmanajementsystem.utils.constants;

public class ResponseMessage {
    // Success Messages
    public static final String SUCCES_CREATE_DATA = "data berhasil ditambahkan";
    public static final String SUCCES_LOGIN = "user berhasil masuk";
    public static final String SUCCES_GET_DATA = "data berhasil ditemukan";
    public static final String SUCCES_UPDATE_DATA = "data berhasil diperbaharui";
    public static final String SUCCES_DELETE_DATA = "data berhasil dihapus";

    // Error & Exception Messages
    public static final String UNAUTHORIZED = "user is unauthorized";
    public static final String NOT_FOUND = "data not found";
    public static final String ACCESS_DENIED = "Access denied: You do not have permission to access this resource";
    public static final String VALIDATION_ERROR = "Validation error on payload";
    public static final String DATABASE_ERROR = "Database constraint violation";
    public static final String INTERNAL_SERVER_ERROR = "An unexpected error occurred";
}
