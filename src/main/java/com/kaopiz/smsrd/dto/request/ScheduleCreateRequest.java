package com.kaopiz.smsrd.dto.request;

import com.kaopiz.smsrd.model.Schedule;
import com.kaopiz.smsrd.model.ScheduleInvitation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleCreateRequest {

    @NotNull
    private Long enterpriseId;

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

    private Schedule.AlertType alertType;  // "10分前", "30分前", "1時間前", "1日前", "カスタム"

    private Integer alertCustomMinutes; // Required if alertType is "カスタム"

    @Size(max = 1000)
    private String content;

    private List<@Valid InvitationRequest> invitations;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InvitationRequest {
        @NotNull(message = "Invitee type is required")
        private ScheduleInvitation.InviteeType inviteeType;

        private Long inviteeWorkerId;

        private Long inviteeVendorId;

        @AssertTrue(message = "Worker ID or Vendor ID must be provided based on invitee type")
        public boolean isValidInvitee() {
            if (inviteeType == null) return false;
            
            if (inviteeType == ScheduleInvitation.InviteeType.WORKER) {
                return inviteeWorkerId != null && inviteeWorkerId > 0;
            }
            if (inviteeType == ScheduleInvitation.InviteeType.VENDOR) {
                return inviteeVendorId != null && inviteeVendorId > 0;
            }
            return false;
        }
    }

    @AssertTrue(message = "Start must be before end")
    public boolean isStartBeforeEnd() {
        if (startDateTime == null || endDateTime == null) return true;
        return startDateTime.isBefore(endDateTime);
    }

    @AssertTrue(message = "If schedule is not public, at least one invitation is required")
    public boolean isValidInvitations() {
        if (!isPublic) {
            return invitations != null && !invitations.isEmpty();
        }
        return true;
    }

    @AssertTrue(message = "Custom minutes required for custom alert")
    public boolean isAlertCustomValid() {
        if (alertType == null) return true;

        if (alertType == Schedule.AlertType.CUSTOM) {
            return alertCustomMinutes != null && alertCustomMinutes > 0;
        }

        return alertCustomMinutes == null;
    }
}
