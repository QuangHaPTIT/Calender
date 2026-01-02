package com.kaopiz.smsrd.model;

import com.kaopiz.smsrd.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "personnel_assignments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class PersonnelAssignment extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personnel_id", nullable = false)
    private Personnel personnel;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false)
    private ConstructionSite site;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @Column(name = "contract_level")
    private Integer contractLevel;

    @Column(name = "assignment_begin")
    private LocalDateTime assignmentBegin;

    @Column(name = "assignment_end")
    private LocalDateTime assignmentEnd;

    public PersonnelAssignment(
            Personnel personnel,
            ConstructionSite site,
            Vendor vendor,
            Integer contractLevel,
            LocalDateTime assignmentBegin,
            LocalDateTime assignmentEnd
    ) {
        this.personnel = personnel;
        this.site = site;
        this.vendor = vendor;
        this.contractLevel = contractLevel;
        this.assignmentBegin = assignmentBegin;
        this.assignmentEnd = assignmentEnd;
    }
}

