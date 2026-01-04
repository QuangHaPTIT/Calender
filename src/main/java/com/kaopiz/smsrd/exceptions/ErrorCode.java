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
    PASSWORD_TOO_SHORT("validation.password.min_length"),

    SCHEDULE_NOT_FOUND("schedule.not_found"),
    CONSTRUCTION_SITE_NOT_FOUND("construction.site.not_found"),
    CATEGORY_NOT_FOUND("schedule.category.not_found"),
    PERSONNEL_NOT_FOUND("personnel.not_found"),
    VENDOR_NOT_FOUND("vendor.not_found"),
    SCHEDULE_UNAUTHORIZED("schedule.unauthorized"),
    SCHEDULE_TIME_INVALID("schedule.time.invalid"),
    SCHEDULE_ALERT_INVALID("schedule.alert.invalid"),
    SCHEDULE_INVITATION_INVALID("schedule.invitation.invalid"),
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
