CREATE TABLE personnel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_number VARCHAR(255) UNIQUE NOT NULL COMMENT 'Employee identifier from Forxai',
    vendor_id BIGINT NOT NULL COMMENT 'Indicates the vendor/company the employee belongs to',
    last_name VARCHAR(255),
    first_name VARCHAR(255),
    FOREIGN KEY (vendor_id) REFERENCES vendors(id) ON DELETE CASCADE,
    INDEX idx_employee_number (employee_number),
    INDEX idx_vendor_id (vendor_id)
);