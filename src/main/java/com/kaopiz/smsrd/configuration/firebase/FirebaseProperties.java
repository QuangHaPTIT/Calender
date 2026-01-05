package com.kaopiz.smsrd.configuration.firebase;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "firebase")
public class FirebaseProperties {
    private String credentialsPath;
    private String databaseUrl;
    private String projectId;
    private boolean enabled = true;
    private int connectTimeoutSeconds;
    private int readTimeoutSeconds;
}
