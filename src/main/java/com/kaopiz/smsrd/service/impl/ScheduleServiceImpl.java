package com.kaopiz.smsrd.service.impl;

import com.kaopiz.smsrd.dto.request.ScheduleCreateRequest;
import com.kaopiz.smsrd.dto.request.ScheduleUpdateRequest;
import com.kaopiz.smsrd.dto.response.ScheduleResponse;
import com.kaopiz.smsrd.exceptions.ConstraintViolationException;
import com.kaopiz.smsrd.exceptions.ErrorCode;
import com.kaopiz.smsrd.mapper.ScheduleMapper;
import com.kaopiz.smsrd.model.*;
import com.kaopiz.smsrd.repository.*;
import com.kaopiz.smsrd.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ConstructionSiteRepository siteRepository;
    private final PersonnelRepository personnelRepository;
    private final ScheduleCategoryRepository categoryRepository;
    private final ScheduleMapper scheduleMapper;
    private final VendorRepository vendorRepository;

    @Override
    public ScheduleResponse createSchedule(ScheduleCreateRequest request, Long currentUserId) {
        var site = findConstructionSite(request.getConstructionSiteId());

        var creator = findPersonnel(currentUserId);

        var category = resolveCategory(request.getCategoryId(), request.getEnterpriseId());

        var schedule = scheduleMapper.toEntity(request, site, creator, category);

        if (!request.isPublic() &&
                request.getInvitations() != null &&
                !request.getInvitations().isEmpty()) {

            var workerMap = new HashMap<Long, Personnel>();
            var vendorMap = new HashMap<Long, Vendor>();
            buildInvitationMaps(request, workerMap, vendorMap);

            var invitations = scheduleMapper.toInvitationEntities(
                    request, schedule, workerMap, vendorMap);

            schedule.setInvitations(invitations);
        }

        schedule = scheduleRepository.save(schedule);
        var savedInvitations = schedule.getInvitations();

        return scheduleMapper.toResponse(schedule, savedInvitations);
    }

    @Override
    public ScheduleResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest request) {
        return null;
    }

    @Override
    public void deleteSchedule(Long scheduleId, Long currentUserId) {

    }

    private void buildInvitationMaps(
            ScheduleCreateRequest request,
            Map<Long, Personnel> workerMap,
            Map<Long, Vendor> vendorMap
    ) {
        if (request.isPublic() || request.getInvitations() == null || request.getInvitations().isEmpty()) return;

        for (var inv : request.getInvitations()) {
            if (inv.getInviteeType() == ScheduleInvitation.InviteeType.WORKER
                    && inv.getInviteeWorkerId() != null) {
                var worker = findPersonnel(inv.getInviteeWorkerId());
                workerMap.put(worker.getId(), worker);
            }

            if (inv.getInviteeType() == ScheduleInvitation.InviteeType.VENDOR
                    && inv.getInviteeVendorId() != null) {
                var vendor = findVendor(inv.getInviteeVendorId());
                vendorMap.put(vendor.getId(), vendor);
            }
        }
    }

    private ConstructionSite findConstructionSite(Long siteId) {
        return siteRepository.findById(siteId)
                .orElseThrow(() -> new ConstraintViolationException(
                        ErrorCode.CONSTRUCTION_SITE_NOT_FOUND,
                        "Construction site not found",
                        List.of("constructionSiteId")));
    }

    private Personnel findPersonnel(Long personnelId) {
        return personnelRepository.findById(personnelId)
                .orElseThrow(() -> new ConstraintViolationException(
                        ErrorCode.PERSONNEL_NOT_FOUND,
                        "Personnel not found",
                        List.of("currentUserId")
                ));
    }

    private ScheduleCategory resolveCategory(Long categoryId, Long enterpriseId) {
        if (categoryId == null) {
            return null;
        }

        return categoryRepository.findByAndEnterpriseId(categoryId, enterpriseId)
                .orElseThrow(() -> new ConstraintViolationException(
                        ErrorCode.CATEGORY_NOT_FOUND,
                        "Category not found",
                        List.of("categoryId")
                ));
    }

    private Vendor findVendor(Long vendorId) {
        return vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ConstraintViolationException(
                        ErrorCode.VENDOR_NOT_FOUND,
                        "Vendor not found",
                        List.of("vendorId")
                ));
    }

}
