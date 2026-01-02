package com.kaopiz.smsrd.model;

import com.kaopiz.smsrd.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "personnel_app_settings")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class PersonnelAppSettings extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "personnel_id", nullable = false, unique = true)
    private Personnel personnel;

    @Size(max = 255)
    @Column(name = "enterprise_id", nullable = false)
    private String enterpriseId;

    @Builder.Default
    @Size(max = 10)
    @Column(name = "language")
    private String language = "ja";

    @Size(max = 50)
    @Column(name = "default_alert_type")
    private String defaultAlertType;

    @Column(name = "default_alert_custom_minutes")
    private Integer defaultAlertCustomMinutes;

    public PersonnelAppSettings(
            Personnel personnel,
            String enterpriseId,
            String language,
            String defaultAlertType,
            Integer defaultAlertCustomMinutes
    ) {
        this.personnel = personnel;
        this.enterpriseId = enterpriseId;
        this.language = language;
        this.defaultAlertType = defaultAlertType;
        this.defaultAlertCustomMinutes = defaultAlertCustomMinutes;
    }
}

