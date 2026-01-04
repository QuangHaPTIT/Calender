package com.kaopiz.smsrd.service;

import com.kaopiz.smsrd.dto.request.ScheduleCreateRequest;
import com.kaopiz.smsrd.dto.request.ScheduleUpdateRequest;
import com.kaopiz.smsrd.dto.response.ScheduleResponse;

public interface ScheduleService {
    ScheduleResponse createSchedule(ScheduleCreateRequest request, Long currentUserId);
    ScheduleResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest request);
    public void deleteSchedule(Long scheduleId, Long currentUserId);
}
