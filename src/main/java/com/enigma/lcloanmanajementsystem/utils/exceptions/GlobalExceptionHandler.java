package com.enigma.lcloanmanajementsystem.utils.exceptions;

import com.enigma.lcloanmanajementsystem.dto.response.CommonResponse;
import com.enigma.lcloanmanajementsystem.utils.helpers.ResponseUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Costum ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CommonResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseUtil.buildResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                null,
                Map.of("error", ex.getMessage())
        );
    }

    // Custom ValidationException
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<CommonResponse<Object>> handleCustomValidationException(ValidationException ex) {
        return ResponseUtil.buildResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                null,
                Map.of("error", ex.getMessage())
        );
    }

    // Spring @Valid MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseUtil.buildResponse(
                HttpStatus.BAD_REQUEST,
                "Validation error on payload",
                null,
                errors
        );
    }

    // Costum BusinessException
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonResponse<Object>> handleBusinessException(BusinessException ex) {
        return ResponseUtil.buildResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                null,
                Map.of("error", ex.getMessage())
        );
    }

    // Security AuthenticationException
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<CommonResponse<Object>> handleAuthenticationException(AuthenticationException ex) {
        return ResponseUtil.buildResponse(
                HttpStatus.UNAUTHORIZED,
                "Authentication failed: " + ex.getMessage(),
                null,
                Map.of("error", ex.getMessage())
        );
    }

    // Security AccessDeniedException
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CommonResponse<Object>> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseUtil.buildResponse(
                HttpStatus.FORBIDDEN,
                "Access denied: You do not have permission to access this resource",
                null,
                Map.of("error", ex.getMessage())
        );
    }

    // DataIntegrityViolationException
    @ExceptionHandler(DataIntegrityViolationException.class)
    public  ResponseEntity<CommonResponse<Object>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String dbErrorMessage = ex.getRootCause().getMessage();
        String errorTitle = "Database error"; // Nilai default (fallback)

        if (dbErrorMessage != null) {
            if (dbErrorMessage.contains("Detail:")) {
                errorTitle = dbErrorMessage.substring(dbErrorMessage.indexOf("Detail:") + 7).trim();
                if (errorTitle.endsWith("]")) {
                    errorTitle = errorTitle.substring(0, errorTitle.length() - 1).trim();
                }
            } else if (dbErrorMessage.contains("ERROR:")) {
                errorTitle = dbErrorMessage.split("\n")[0].replace("ERROR:", "").trim();
            }
        }
        return ResponseUtil.buildResponse(
                HttpStatus.CONFLICT,
                errorTitle,
                null,
                Map.of("error", ex.getMessage())
        );
    }

    // Generic Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Object>> handleGenericException(Exception ex) {
        return ResponseUtil.buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred : "+ex.getClass().getSimpleName(),
                null,
                Map.of("error", ex.getMessage() != null ? ex.getMessage() : "Internal server error")
        );
    }


}
