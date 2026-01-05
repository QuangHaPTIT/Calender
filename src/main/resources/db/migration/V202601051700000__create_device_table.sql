-- Device table for push notifications
CREATE TABLE devices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    personnel_id BIGINT NOT NULL COMMENT 'User sở hữu device',

    fcm_token VARCHAR(500) NOT NULL UNIQUE COMMENT 'Firebase/APNs token',
    sns_endpoint_arn VARCHAR(500) NOT NULL UNIQUE COMMENT 'AWS SNS endpoint ARN',

    platform VARCHAR(20) NOT NULL COMMENT 'ANDROID, IOS, WEB',

    device_id VARCHAR(255) COMMENT 'UUID của device',
    device_name VARCHAR(100) COMMENT 'Tên device hiển thị',
    device_model VARCHAR(100) COMMENT 'Model: iPhone 14 Pro',
    os_version VARCHAR(50) COMMENT 'iOS 17.1, Android 14',
    app_version VARCHAR(50) COMMENT 'App version: 1.0.0',

    is_active BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Device active',
    notification_enabled BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'User bật notification',

    language_code VARCHAR(10) DEFAULT 'ja' COMMENT 'ja, en, ko',
    timezone VARCHAR(50),

    last_active_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    token_refreshed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_devices_personnel
        FOREIGN KEY (personnel_id) REFERENCES personnel(id) ON DELETE CASCADE,

    INDEX idx_personnel_id (personnel_id),
    INDEX idx_fcm_token (fcm_token),
    INDEX idx_sns_endpoint (sns_endpoint_arn),
    INDEX idx_personnel_active (personnel_id, is_active)

);