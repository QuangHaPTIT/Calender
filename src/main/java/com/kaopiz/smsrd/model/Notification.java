package com.kaopiz.smsrd.model;

import com.kaopiz.smsrd.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "notifications")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Notification extends BaseEntity {

    @NotBlank
    @Size(max = 255)
    @Column(name = "enterprise_id", nullable = false)
    private String enterpriseId;

    @NotBlank
    @Size(max = 255)
    @Column(name = "worker_id", nullable = false)
    private String workerId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "notification_type", nullable = false)
    private String notificationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @NotBlank
    @Size(max = 500)
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Builder.Default
    @Column(name = "is_read")
    private Boolean isRead = false;

    @Column(name = "read_at")
    private LocalDateTime readAt;

    public Notification(
            String enterpriseId,
            String workerId,
            String notificationType,
            Schedule schedule,
            String title,
            String message
    ) {
        this.enterpriseId = enterpriseId;
        this.workerId = workerId;
        this.notificationType = notificationType;
        this.schedule = schedule;
        this.title = title;
        this.message = message;
    }
}

