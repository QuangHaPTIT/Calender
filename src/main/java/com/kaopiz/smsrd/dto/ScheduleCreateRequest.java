package com.kaopiz.smsrd.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ScheduleCreateRequest {

    @NotNull
    private Long constructionSiteId;

    private Long categoryId;

    @NotBlank
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @Builder.Default
    private Boolean isAllDay = false;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    @Builder.Default
    private boolean isPublic = false;

    @Size(max = 50)
    private String alertType;  // "10分前", "30分前", "1時間前", "1日前", "カスタム"

    private Integer alertCustomMinutes; // Required if alertType is "カスタム"

    @Size(max = 1000)
    private String content;

    private List<@Valid InvitationRequest> invitations;

    public static class InvitationRequest {
        @NotBlank
        @Size(max = 50)
        private String inviteeType; // "WORKER", "VENDOR"

        private Long inviteeWorkerId;

        private Long inviteeVendorId;
    }

    @Getter
    public static enum AlertType {
        TEN_MINUTES_BEFORE("10分前", 10),
        THIRTY_MINUTES_BEFORE("30分前", 30),
        ONE_HOUR_BEFORE("1時間前", 60),
        ONE_DAY_BEFORE("1日前", 1440),
        CUSTOM("カスタム", null)
        ;

        private final String displayName;
        private final Integer minutes;

        AlertType (String displayName, Integer minutes) {
            this.displayName = displayName;
            this.minutes = minutes;
        }

        public boolean isCustom() {
            return this == CUSTOM;
        }
    }
}
