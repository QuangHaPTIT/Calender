package com.kaopiz.smsrd.service.impl;

import com.kaopiz.smsrd.dto.request.ScheduleCreateRequest;
import com.kaopiz.smsrd.dto.request.ScheduleUpdateRequest;
import com.kaopiz.smsrd.dto.response.ScheduleResponse;
import com.kaopiz.smsrd.exceptions.ConstraintViolationException;
import com.kaopiz.smsrd.exceptions.ErrorCode;
import com.kaopiz.smsrd.model.ScheduleCategory;
import com.kaopiz.smsrd.repository.ConstructionSiteRepository;
import com.kaopiz.smsrd.repository.PersonnelRepository;
import com.kaopiz.smsrd.repository.ScheduleCategoryRepository;
import com.kaopiz.smsrd.repository.ScheduleRepository;
import com.kaopiz.smsrd.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ConstructionSiteRepository siteRepository;
    private final PersonnelRepository personnelRepository;
    private final ScheduleCategoryRepository categoryRepository;

    @Override
    public ScheduleResponse createSchedule(ScheduleCreateRequest request, Long currentUserId) {
        var site = siteRepository.findById(request.getConstructionSiteId())
                .orElseThrow(() -> new ConstraintViolationException(
                        ErrorCode.CONSTRUCTION_SITE_NOT_FOUND,
                        "Construction site not found",
                        List.of("constructionSiteId")));

        var creator = personnelRepository.findById(currentUserId)
                .orElseThrow(() -> new ConstraintViolationException(
                        ErrorCode.PERSONNEL_NOT_FOUND,
                        "Personnel not found: " + currentUserId
                ));

        ScheduleCategory category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ConstraintViolationException(
                            ErrorCode.CATEGORY_NOT_FOUND,
                            "Category not found: " + request.getCategoryId(),
                            List.of("categoryId")
                    ));
        }
        return null;
    }

    @Override
    public ScheduleResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest request) {
        return null;
    }

    @Override
    public void deleteSchedule(Long scheduleId, Long currentUserId) {

    }


}
