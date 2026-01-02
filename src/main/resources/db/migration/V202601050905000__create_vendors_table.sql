CREATE TABLE vendors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    vendor_id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    INDEX idx_vendor_id (vendor_id)
);
