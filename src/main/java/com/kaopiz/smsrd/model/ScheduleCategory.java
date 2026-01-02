package com.kaopiz.smsrd.model;

import com.kaopiz.smsrd.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "schedule_categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class ScheduleCategory extends BaseEntity {

    @NotBlank
    @Size(max = 255)
    @Column(name = "enterprise_id", nullable = false)
    private String enterpriseId;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name_ja", nullable = false)
    private String nameJa;

    @Size(max = 255)
    @Column(name = "name_en")
    private String nameEn;

    @Size(max = 255)
    @Column(name = "name_ko")
    private String nameKo;

    @Builder.Default
    @Column(name = "display_order")
    private Integer displayOrder = 0;

    @Builder.Default
    @Column(name = "is_active")
    private Boolean isActive = true;

    public ScheduleCategory(
            String enterpriseId,
            String nameJa,
            String nameEn,
            String nameKo,
            Integer displayOrder,
            Boolean isActive
    ) {
        this.enterpriseId = enterpriseId;
        this.nameJa = nameJa;
        this.nameEn = nameEn;
        this.nameKo = nameKo;
        this.displayOrder = displayOrder;
        this.isActive = isActive;
    }
}

