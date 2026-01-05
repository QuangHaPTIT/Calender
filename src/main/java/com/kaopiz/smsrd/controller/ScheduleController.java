package com.kaopiz.smsrd.controller;

import com.kaopiz.smsrd.dto.request.ScheduleCreateRequest;
import com.kaopiz.smsrd.dto.response.Response;
import com.kaopiz.smsrd.dto.response.ScheduleResponse;
import com.kaopiz.smsrd.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public Response<ScheduleResponse> createSchedule(@RequestBody @Valid ScheduleCreateRequest request) {
        return Response.of(scheduleService.createSchedule(request, 1L));
    }
}
