CREATE TABLE schedule_invitations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    schedule_id BIGINT NOT NULL COMMENT 'References schedules',
    invitee_type VARCHAR(50) NOT NULL COMMENT 'WORKER or VENDOR',
    invitee_worker_id BIGINT NULL COMMENT 'References personnel if type WORKER',
    invitee_vendor_id BIGINT NULL COMMENT 'References vendors if type VENDOR',
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (schedule_id) REFERENCES schedules(id) ON DELETE CASCADE,
    FOREIGN KEY (invitee_worker_id) REFERENCES personnel(id) ON DELETE CASCADE,
    FOREIGN KEY (invitee_vendor_id) REFERENCES vendors(id) ON DELETE CASCADE,
    INDEX idx_schedule (schedule_id),
    INDEX idx_invitee_worker (invitee_worker_id),
    INDEX idx_invitee_vendor (invitee_vendor_id),
    CONSTRAINT chk_invitee CHECK (
        (invitee_type = 'WORKER' AND invitee_worker_id IS NOT NULL AND invitee_vendor_id IS NULL) OR
        (invitee_type = 'VENDOR' AND invitee_vendor_id IS NOT NULL AND invitee_worker_id IS NULL)
    )
);