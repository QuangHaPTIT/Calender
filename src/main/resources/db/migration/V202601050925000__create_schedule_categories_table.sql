CREATE TABLE schedule_categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    enterprise_id BIGINT NOT NULL COMMENT 'Tenant ID',
    name_ja VARCHAR(255) NOT NULL COMMENT 'Category name Japanese',
    name_en VARCHAR(255) COMMENT 'Category name English Phase 2',
    name_ko VARCHAR(255) COMMENT 'Category name Korean Phase 2',
    display_order INT DEFAULT 0 COMMENT 'Display order',
    is_active BOOLEAN DEFAULT TRUE COMMENT 'Active flag',
    created_at DATETIME,
    updated_at DATETIME,
    INDEX idx_enterprise_active (enterprise_id, is_active)
);