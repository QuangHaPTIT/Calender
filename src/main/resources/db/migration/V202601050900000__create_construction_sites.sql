CREATE TABLE construction_sites (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    construction_code VARCHAR(255) UNIQUE NOT NULL,
    construction_name VARCHAR(255),
    construction_begin DATETIME NULL,
    construction_end DATETIME NULL,
    INDEX idx_construction_code (construction_code)
);