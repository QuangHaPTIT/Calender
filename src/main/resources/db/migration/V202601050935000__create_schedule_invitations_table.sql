CREATE TABLE schedule_invitations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    schedule_id BIGINT NOT NULL COMMENT 'Schedule ID',
    invitee_type VARCHAR(50) NOT NULL COMMENT 'WORKER or VENDOR',
    invitee_worker_id VARCHAR(255) COMMENT 'Worker ID if type WORKER',
    invitee_vendor_id VARCHAR(255) COMMENT 'Vendor ID if type VENDOR',
    created_at DATETIME,
    FOREIGN KEY (schedule_id) REFERENCES schedules(id) ON DELETE CASCADE,
    INDEX idx_schedule (schedule_id),
    INDEX idx_invitee_worker (invitee_worker_id),
    INDEX idx_invitee_vendor (invitee_vendor_id)
);