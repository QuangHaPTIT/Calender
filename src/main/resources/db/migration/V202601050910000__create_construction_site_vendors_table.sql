CREATE TABLE construction_site_vendors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    site_id BIGINT NOT NULL COMMENT 'References construction_sites',
    vendor_id BIGINT NOT NULL COMMENT 'References vendors',
    hierarchy_path VARCHAR(72),
    assignment_begin DATETIME NULL,
    assignment_end DATETIME NULL,
    FOREIGN KEY (site_id) REFERENCES construction_sites(id) ON DELETE CASCADE,
    FOREIGN KEY (vendor_id) REFERENCES vendors(id) ON DELETE CASCADE,
    INDEX idx_site_vendor (site_id, vendor_id)
);
