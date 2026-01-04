package com.kaopiz.smsrd.dto.response;

import com.kaopiz.smsrd.dto.request.ScheduleCreateRequest;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ScheduleResponse {
    private Long id;
    private String title;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Boolean isAllDay;
    private Boolean isPublic;
    private ScheduleCreateRequest.AlertType alertType;
    private Integer alertCustomMinutes;
    private String content;

    @Getter
    @Builder
    public static class CategoryDto {
        private Long id;
        private String nameJa;
    }

    @Getter
    @Builder
    public static class ConstructionSiteDto {
        private Long id;
        private String constructionCode;
        private String constructionName;
    }

    @Getter
    @Builder
    public static class CreatorDto {
        private Long workerId;
        private String workerName;
        private String vendorName;
    }

    @Getter
    @Builder
    public static class InvitationDto {
        private Long id;
        private String inviteeId;
        private String inviteeName;
        private String vendorName; // for WORKER type
    }
}
