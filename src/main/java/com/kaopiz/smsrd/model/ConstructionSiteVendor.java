package com.kaopiz.smsrd.model;

import com.kaopiz.smsrd.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "construction_site_vendors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class ConstructionSiteVendor extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    private ConstructionSite site;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @Size(max = 72)
    @Column(name = "hierarchy_path")
    private String hierarchyPath;

    @Column(name = "assignment_begin")
    private LocalDateTime assignmentBegin;

    @Column(name = "assignment_end")
    private LocalDateTime assignmentEnd;

    public ConstructionSiteVendor(
            ConstructionSite site,
            Vendor vendor,
            String hierarchyPath,
            LocalDateTime assignmentBegin,
            LocalDateTime assignmentEnd
    ) {
        this.site = site;
        this.vendor = vendor;
        this.hierarchyPath = hierarchyPath;
        this.assignmentBegin = assignmentBegin;
        this.assignmentEnd = assignmentEnd;
    }
}

