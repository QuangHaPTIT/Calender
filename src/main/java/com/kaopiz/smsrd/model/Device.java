package com.kaopiz.smsrd.model;

import com.kaopiz.smsrd.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Device extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "personnel_id", nullable = false)
    private Personnel personnel;

    @Column(name = "fcm_token", nullable = false, unique = true, length = 500)
    private String fcmToken;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform", nullable = false, length = 20)
    private Platform platform;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "os_version", length = 50)
    private String osVersion;

    @Column(name = "app_version", length = 50)
    private String appVersion;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "language_code", length = 10)
    @Builder.Default
    private String languageCode = "ja";

    @Column(name = "timezone", length = 50)
    @Builder.Default
    private String timezone = "Asia/Tokyo";

    @Column(name = "notification_enabled", nullable = false)
    @Builder.Default
    private Boolean notificationEnabled = true; // User's notification preference

    @Column(name = "last_active_at")
    private LocalDateTime lastActiveAt;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;

    @Column(name = "token_refreshed_at")
    private LocalDateTime tokenRefreshedAt;

    public enum Platform {
        IOS,
        ANDROID,
        WEB
    }

    @PrePersist
    protected void onCreate() {
        if (registeredAt == null) registeredAt = LocalDateTime.now();
        if (lastActiveAt == null) lastActiveAt = LocalDateTime.now();
        if (tokenRefreshedAt == null) tokenRefreshedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastActiveAt = LocalDateTime.now();
    }

    public void updateFcmToken(String newToken) {
        this.fcmToken = newToken;
        this.tokenRefreshedAt = LocalDateTime.now();
        this.isActive = true;
    }

    /* Mark device as inactive (when user logs out or uninstalls app) */
    public void deactivate() {
        this.isActive = false;
    }
}
