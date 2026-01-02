package com.kaopiz.smsrd.model;

import com.kaopiz.smsrd.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class Schedule extends BaseEntity {

    @NotBlank
    @Size(max = 255)
    @Column(name = "enterprise_id", nullable = false)
    private String enterpriseId;

    @NotBlank
    @Size(max = 255)
    @Column(name = "construction_site_id", nullable = false)
    private String constructionSiteId;

    @ManyToOne
    @JoinColumn(name = "creator_worker_id", referencedColumnName = "id", nullable = false)
    private Personnel creatorWorker;
    @ManyToOne
    @JoinColumn(name = "creator_vendor_id", referencedColumnName = "id", nullable = false)
    private Vendor creatorVendor;


    @NotBlank
    @Size(max = 500)
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ScheduleCategory category;

    @Builder.Default
    @Column(name = "is_all_day")
    private Boolean isAllDay = false;

    @NotNull
    @Column(name = "start_datetime", nullable = false)
    private LocalDateTime startDatetime;

    @NotNull
    @Column(name = "end_datetime", nullable = false)
    private LocalDateTime endDatetime;

    @Builder.Default
    @Column(name = "is_public")
    private Boolean isPublic = false;

    @Size(max = 50)
    @Column(name = "alert_type")
    private String alertType;

    @Column(name = "alert_custom_minutes")
    private Integer alertCustomMinutes;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}

