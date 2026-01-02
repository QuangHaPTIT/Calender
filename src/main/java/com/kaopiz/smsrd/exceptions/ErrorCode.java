package com.kaopiz.smsrd.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND("users.not_found"),
    CALENDER_NOT_FOUND("calender.not_found"),

    VALIDATION_FAILED("validation.failed"),
    REQUIRED("validation.required"),
    INVALID_FORMAT("validation.invalid_format"),
    EMAIL_REQUIRED("validation.email.required"),
    EMAIL_INVALID("validation.email.invalid"),
    PASSWORD_TOO_SHORT("validation.password.min_length");
    ;

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public UserError toUserError(String message) {
        return UserError.builder()
                .code(this.code)
                .message(message)
                .build();
    }

    public UserError toUserError(String message, List<String> fields) {
        return UserError.builder()
                .code(this.code)
                .message(message)
                .fields(fields)
                .build();
    }
}
