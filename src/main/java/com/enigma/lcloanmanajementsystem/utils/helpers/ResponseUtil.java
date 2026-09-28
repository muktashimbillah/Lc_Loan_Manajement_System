package com.enigma.lcloanmanajementsystem.utils.helpers;

import com.enigma.lcloanmanajementsystem.dto.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseUtil {
    public static <T> ResponseEntity<CommonResponse<T>> buildResponse(HttpStatus httpStatus, String message, T data) {
        CommonResponse<T> response = CommonResponse.<T>builder()
                .status(httpStatus.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(httpStatus).body(response);
    }
    public static <T> ResponseEntity<CommonResponse<T>> buildResponse(HttpStatus httpStatus, String message, T data, Map<String, String> errors) {
        CommonResponse<T> response = CommonResponse.<T>builder()
                .status(httpStatus.value())
                .message(message)
                .data(data)
                .errors(errors)
                .build();

        return ResponseEntity.status(httpStatus).body(response);
    }

}
