package com.kaopiz.smsrd.exceptions;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserError {
    private String code;
    private String message;
    private List<String> fields;

    public static UserError of(ErrorCode errorCode, String message) {
        return UserError.builder()
                .code(errorCode.getCode())
                .message(message)
                .build();
    }

    public static UserError of(ErrorCode errorCode, String message, List<String> fields) {
        return UserError.builder()
                .code(errorCode.getCode())
                .message(message)
                .fields(fields)
                .build();
    }
}
