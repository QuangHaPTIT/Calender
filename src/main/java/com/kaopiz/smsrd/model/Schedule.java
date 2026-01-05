package com.kaopiz.smsrd.model;

import com.kaopiz.smsrd.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class Schedule extends BaseEntity {

    @Column(name = "enterprise_id", nullable = false)
    private Long enterpriseId;

    @ManyToOne
    @JoinColumn(name = "construction_site_id", nullable = false)
    private ConstructionSite constructionSite;

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

    @Column(name = "alert_type")
    @Enumerated(EnumType.STRING)
    private AlertType alertType;

    @Column(name = "alert_custom_minutes")
    private Integer alertCustomMinutes;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<ScheduleInvitation> invitations = new ArrayList<>();

    @Getter
    public enum AlertType {
        TEN_MINUTES_BEFORE( 10),
        THIRTY_MINUTES_BEFORE( 30),
        ONE_HOUR_BEFORE( 60),
        ONE_DAY_BEFORE(1440),
        CUSTOM(null)
        ;

        private final Integer minutes;

        AlertType (Integer minutes) {
            this.minutes = minutes;
        }

        public boolean isCustom() {
            return this == CUSTOM;
        }
    }

}

