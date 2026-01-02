CREATE TABLE personnel_assignments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    personnel_id BIGINT NOT NULL COMMENT 'References personnel',
    site_id BIGINT NOT NULL COMMENT 'References construction sites',
    vendor_id BIGINT NOT NULL COMMENT 'References vendors',
    contract_level INT,
    assignment_begin TIMESTAMP NULL,
    assignment_end TIMESTAMP NULL,
    FOREIGN KEY (personnel_id) REFERENCES personnel(id) ON DELETE CASCADE,
    FOREIGN KEY (site_id) REFERENCES construction_sites(id) ON DELETE CASCADE,
    FOREIGN KEY (vendor_id) REFERENCES vendors(id) ON DELETE CASCADE,
    INDEX idx_personnel_site (personnel_id, site_id),
    INDEX idx_site_personnel (site_id, personnel_id)
);
