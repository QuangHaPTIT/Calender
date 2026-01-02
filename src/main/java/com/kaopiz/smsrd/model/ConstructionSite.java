package com.kaopiz.smsrd.model;

import com.kaopiz.smsrd.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "construction_sites")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class ConstructionSite extends BaseEntity {

    @NotBlank
    @Size(max = 255)
    @Column(name = "construction_code", unique = true, nullable = false)
    private String constructionCode;

    @Size(max = 255)
    @Column(name = "construction_name")
    private String constructionName;

    @Column(name = "construction_begin")
    private LocalDateTime constructionBegin;

    @Column(name = "construction_end")
    private LocalDateTime constructionEnd;

    public ConstructionSite(
            String constructionCode,
            String constructionName,
            LocalDateTime constructionBegin,
            LocalDateTime constructionEnd
    ) {
        this.constructionCode = constructionCode;
        this.constructionName = constructionName;
        this.constructionBegin = constructionBegin;
        this.constructionEnd = constructionEnd;
    }
}

