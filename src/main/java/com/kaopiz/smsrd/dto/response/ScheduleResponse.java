package com.kaopiz.smsrd.dto.response;

import com.kaopiz.smsrd.model.Schedule;
import com.kaopiz.smsrd.model.ScheduleInvitation;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ScheduleResponse {
    private Long id;
    private String title;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Boolean isAllDay;
    private Boolean isPublic;
    private Schedule.AlertType alertType;
    private Integer alertCustomMinutes;
    private String content;

    private CategoryDto category;
    private ConstructionSiteDto constructionSite;
    private CreatorDto creator;
    private List<InvitationDto> invitations;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


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
        private ScheduleInvitation.InviteeType inviteeType;
        private Long inviteeWorkerId;
        private String inviteeWorkerName;
        private Long inviteeVendorId;
        private String inviteeVendorName;
    }
}
