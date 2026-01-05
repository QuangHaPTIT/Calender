CREATE TABLE notifications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    enterprise_id BIGINT NOT NULL COMMENT 'Tenant ID',
    worker_id VARCHAR(255) NOT NULL COMMENT 'Receiver Worker ID',
    notification_type VARCHAR(50) NOT NULL COMMENT 'INVITED or UPDATED or DELETED',
    schedule_id BIGINT NULL COMMENT 'Related schedule nullable',
    title VARCHAR(500) NOT NULL COMMENT 'Notification title',
    message TEXT COMMENT 'Notification message',
    is_read BOOLEAN DEFAULT FALSE COMMENT 'Read flag',
    created_at DATETIME,
    read_at DATETIME NULL COMMENT 'When user read notification',
    FOREIGN KEY (schedule_id) REFERENCES schedules(id) ON DELETE SET NULL,
    INDEX idx_worker_read (worker_id, is_read),
    INDEX idx_enterprise (enterprise_id),
    INDEX idx_created (created_at)
);
