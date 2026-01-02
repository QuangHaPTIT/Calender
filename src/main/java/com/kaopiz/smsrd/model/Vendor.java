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

@Getter
@Setter
@Entity
@Table(name = "vendors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Vendor extends BaseEntity {

    @NotBlank
    @Size(max = 255)
    @Column(name = "vendor_id", nullable = false)
    private String vendorId;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    public Vendor(String vendorId, String name) {
        this.vendorId = vendorId;
        this.name = name;
    }
}

