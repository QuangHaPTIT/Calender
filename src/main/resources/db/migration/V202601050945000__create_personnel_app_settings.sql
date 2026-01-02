CREATE TABLE personnel_app_settings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    personnel_id BIGINT NOT NULL COMMENT 'References personnel table',
    enterprise_id VARCHAR(255) NOT NULL COMMENT 'Tenant ID for isolation',
    language VARCHAR(10) DEFAULT 'ja' COMMENT 'ja or en or ko Phase 1 ja only',
    default_alert_type VARCHAR(50) COMMENT 'Default alert setting',
    default_alert_custom_minutes INT COMMENT 'Default custom minutes',
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (personnel_id) REFERENCES personnel(id) ON DELETE CASCADE,
    UNIQUE KEY uk_personnel (personnel_id),
    INDEX idx_enterprise (enterprise_id)
);