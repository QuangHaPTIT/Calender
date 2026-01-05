package com.kaopiz.smsrd.model;

import com.kaopiz.smsrd.model.base.BaseEntity;
import jakarta.persistence.*;
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

    @NotNull
    @Enumerated(EnumType.STRING)
    private InviteeType inviteeType;

    @ManyToOne
    @JoinColumn(name = "invitee_worker_id", referencedColumnName = "id")
    private Personnel inviteeWorker;

    @ManyToOne
    @JoinColumn(name = "invitee_vendor_id", referencedColumnName = "id")
    private Vendor inviteeVendor;

    public enum InviteeType {
        WORKER,
        VENDOR
    }

}

