package com.kaopiz.smsrd.model;

import com.kaopiz.smsrd.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "personnel")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Personnel extends BaseEntity {

    @NotBlank
    @Size(max = 255)
    @Column(name = "employee_number", unique = true, nullable = false)
    private String employeeNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @Size(max = 255)
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 255)
    @Column(name = "first_name")
    private String firstName;

    public Personnel(
            String employeeNumber,
            Vendor vendor,
            String lastName,
            String firstName
    ) {
        this.employeeNumber = employeeNumber;
        this.vendor = vendor;
        this.lastName = lastName;
        this.firstName = firstName;
    }
}

