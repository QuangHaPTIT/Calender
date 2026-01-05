package com.kaopiz.smsrd.configuration.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(
        prefix = "firebase",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class FirebaseConfig {
    private final FirebaseProperties firebaseProperties;
    private final ResourceLoader resourceLoader;
    private FirebaseApp firebaseApp;

    @PostConstruct
    public void initialize() {
        if (!FirebaseApp.getApps().isEmpty()) {
            firebaseApp = FirebaseApp.getInstance();
            return;
        }

        try {
            GoogleCredentials credentials = loadCredential();

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .setDatabaseUrl(firebaseProperties.getDatabaseUrl())
                    .setProjectId(firebaseProperties.getProjectId())
                    .setConnectTimeout((int) TimeUnit.SECONDS.toMillis(firebaseProperties.getConnectTimeoutSeconds()))
                    .setReadTimeout((int) TimeUnit.SECONDS.toMillis(firebaseProperties.getReadTimeoutSeconds()))
                    .build();

            firebaseApp = FirebaseApp.initializeApp(options);
            log.info("Firebase Admin SDK initialized successfully. Project: {}", firebaseApp.getOptions().getProjectId());
        } catch (IOException e) {
            throw new RuntimeException("Cannot initialize Firebase: " + e.getMessage(), e);
        }
    }

    @Bean
    public FirebaseMessaging firebaseMessaging() {
        if (firebaseApp == null) {
            throw new IllegalStateException("FirebaseApp not initialized");
        }

        return FirebaseMessaging.getInstance(firebaseApp);
    }

    @PreDestroy
    public void destroy() {
        if (firebaseApp != null) {
            log.info("Shutting down Firebase Admin SDK");
            try {
                firebaseApp.delete();
                log.info("Firebase Admin SDK shut down successfully");
            } catch (Exception e) {
                log.error("Error shutting down Firebase Admin SDK", e);
            }
        }
    }

    private GoogleCredentials loadCredential() throws IOException{
        String path = firebaseProperties.getCredentialsPath();

        try {
            Resource resource = resourceLoader.getResource(path);

            if (!resource.exists()) {
                throw new IOException("Firebase credentials file not found: " + path);
            }

            try (InputStream inputStream = resource.getInputStream()) {
                return GoogleCredentials.fromStream(inputStream);
            }
        } catch (IOException e) {
            throw new IOException("Cannot load Firebase credentials: " + e.getMessage(), e);
        }
    }
}
