package com.kaopiz.smsrd.mapper;

import com.kaopiz.smsrd.dto.request.ScheduleCreateRequest;
import com.kaopiz.smsrd.dto.response.ScheduleResponse;
import com.kaopiz.smsrd.model.ConstructionSite;
import com.kaopiz.smsrd.model.Personnel;
import com.kaopiz.smsrd.model.Schedule;
import com.kaopiz.smsrd.model.ScheduleCategory;
import com.kaopiz.smsrd.model.ScheduleInvitation;
import com.kaopiz.smsrd.model.Vendor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {

    public ScheduleResponse toResponse(Schedule schedule) {
        return toResponse(schedule, List.of());
    }

    public ScheduleResponse toResponse(Schedule schedule, List<ScheduleInvitation> invitations) {
        return ScheduleResponse.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .startDateTime(schedule.getStartDatetime())
                .endDateTime(schedule.getEndDatetime())
                .isAllDay(schedule.getIsAllDay())
                .isPublic(schedule.getIsPublic())
                .alertType(schedule.getAlertType())
                .alertCustomMinutes(schedule.getAlertCustomMinutes())
                .content(schedule.getContent())
                .category(mapCategory(schedule.getCategory()))
                .constructionSite(mapConstructionSite(schedule))
                .creator(mapCreator(schedule))
                .invitations(mapInvitations(invitations))
                .createdAt(schedule.getCreatedAt())
                .updatedAt(schedule.getUpdatedAt())
                .build();
    }

    public Schedule toEntity(
            ScheduleCreateRequest request,
            ConstructionSite constructionSite,
            Personnel creator,
            ScheduleCategory category) {

        return Schedule.builder()
                .enterpriseId(request.getEnterpriseId())
                .constructionSite(constructionSite)
                .creatorWorker(creator)
                .creatorVendor(creator.getVendor())
                .title(request.getTitle())
                .category(category)
                .isAllDay(request.getIsAllDay())
                .startDatetime(request.getStartDateTime())
                .endDatetime(request.getEndDateTime())
                .isPublic(request.isPublic())
                .alertType(request.getAlertType())
                .alertCustomMinutes(request.getAlertCustomMinutes())
                .content(request.getContent())
                .build();
    }

    public List<ScheduleInvitation> toInvitationEntities(
            ScheduleCreateRequest request,
            Schedule schedule,
            java.util.Map<Long, Personnel> workerMap,
            java.util.Map<Long, Vendor> vendorMap) {

        if (request.isPublic() ||
                request.getInvitations() == null ||
                request.getInvitations().isEmpty()) {
            return List.of();
        }

        return request.getInvitations().stream()
                .map(inv -> buildInvitation(inv, schedule, workerMap, vendorMap))
                .collect(Collectors.toList());
    }

    private ScheduleInvitation buildInvitation(
            ScheduleCreateRequest.InvitationRequest request,
            Schedule schedule,
            java.util.Map<Long, Personnel> workerMap,
            java.util.Map<Long, Vendor> vendorMap) {

        var builder = ScheduleInvitation.builder()
                .schedule(schedule)
                .inviteeType(request.getInviteeType());

        if (request.getInviteeType() == ScheduleInvitation.InviteeType.WORKER
                && request.getInviteeWorkerId() != null) {
            var worker = workerMap.get(request.getInviteeWorkerId());
            if (worker != null) {
                builder.inviteeWorker(worker);
            }
        }

        if (request.getInviteeType() == ScheduleInvitation.InviteeType.VENDOR
                && request.getInviteeVendorId() != null) {
            var vendor = vendorMap.get(request.getInviteeVendorId());
            if (vendor != null) {
                builder.inviteeVendor(vendor);
            }
        }

        return builder.build();
    }

    private ScheduleResponse.CategoryDto mapCategory(ScheduleCategory category) {
        if (category == null) return null;
        return ScheduleResponse.CategoryDto.builder()
                .id(category.getId())
                .nameJa(category.getNameJa())
                .build();
    }

    private ScheduleResponse.ConstructionSiteDto mapConstructionSite(Schedule schedule) {
        if (schedule.getConstructionSite() == null) return null;
        
        var site = schedule.getConstructionSite();
        return ScheduleResponse.ConstructionSiteDto.builder()
                .id(site.getId())
                .constructionCode(site.getConstructionCode())
                .constructionName(site.getConstructionName())
                .build();
    }

    private ScheduleResponse.CreatorDto mapCreator(Schedule schedule) {
        if (schedule.getCreatorWorker() == null) return null;

        var worker = schedule.getCreatorWorker();
        var vendor = schedule.getCreatorVendor();

        return ScheduleResponse.CreatorDto.builder()
                .workerId(worker.getId())
                .workerName(buildWorkerName(worker))
                .vendorName(vendor != null ? vendor.getName() : null)
                .build();
    }

    private List<ScheduleResponse.InvitationDto> mapInvitations(List<ScheduleInvitation> invitations) {
        if (invitations == null || invitations.isEmpty()) {
            return List.of();
        }

        return invitations.stream()
                .map(this::mapInvitation)
                .collect(Collectors.toList());
    }

    private ScheduleResponse.InvitationDto mapInvitation(ScheduleInvitation invitation) {
        var builder = ScheduleResponse.InvitationDto.builder()
                .id(invitation.getId())
                .inviteeType(invitation.getInviteeType());

        if (invitation.getInviteeType() == ScheduleInvitation.InviteeType.WORKER
                && invitation.getInviteeWorker() != null) {
            var worker = invitation.getInviteeWorker();
            builder.inviteeWorkerId(worker.getId())
                    .inviteeWorkerName(buildWorkerName(worker))
                    .inviteeVendorName(worker.getVendor() != null ? worker.getVendor().getName() : null);
        }

        if (invitation.getInviteeType() == ScheduleInvitation.InviteeType.VENDOR
                && invitation.getInviteeVendor() != null) {
            var vendor = invitation.getInviteeVendor();
            builder.inviteeVendorId(vendor.getId())
                    .inviteeVendorName(vendor.getName());
        }

        return builder.build();
    }

    private String buildWorkerName(Personnel worker) {
        if (worker == null) return null;
        var parts = new StringBuilder();
        if (worker.getFirstName() != null) {
            parts.append(worker.getFirstName());
        }
        if (worker.getLastName() != null) {
            if (parts.length() > 0) parts.append(" ");
            parts.append(worker.getLastName());
        }
        return parts.length() > 0 ? parts.toString() : null;
    }
}
