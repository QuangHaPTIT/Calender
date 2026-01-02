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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "schedule_invitations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class ScheduleInvitation extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @NotBlank
    @Size(max = 50)
    @Column(name = "invitee_type", nullable = false)
    private String inviteeType;

    @ManyToOne
    @JoinColumn(name = "invitee_worker_id", referencedColumnName = "id")
    private Personnel inviteeWorker;

    @ManyToOne
    @JoinColumn(name = "invitee_vendor_id", referencedColumnName = "id")
    private Vendor inviteeVendor;

}

