package com.kaopiz.smsrd.configuration.aws;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

import java.net.URI;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AwsSnsConfiguration {

    private final AwsSnsProperties snsProperties;

    private AwsCredentialsProvider getCredentialsProvider() {
        if (StringUtils.hasText(snsProperties.getAccessKey()) && StringUtils.hasText(snsProperties.getSecretKey())) {

            log.info("Using static AWS credentials for SNS");
            return StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(
                            snsProperties.getAccessKey(),
                            snsProperties.getSecretKey()
                    )
            );
        }

        log.info("Using AWS default credential chain for SNS");
        return DefaultCredentialsProvider.create();
    }

    @Bean
    @ConditionalOnProperty(
            prefix = "aws.sns",
            name = "enabled",
            havingValue = "true",
            matchIfMissing = true
    )
    public SnsClient snsClient() {
        try {
            var builder = SnsClient.builder()
                    .region(Region.of(snsProperties.getRegion()))
                    .credentialsProvider(getCredentialsProvider());

            if (StringUtils.hasText(snsProperties.getEndpointOverride())) {
                builder.endpointOverride(URI.create(snsProperties.getEndpointOverride()));
            }

            SnsClient client = builder.build();
            log.info("AWS SNS Client initialized successfully");
            return client;

        } catch (Exception e) {
            log.error("Failed to initialize AWS SNS Client", e);
            throw new RuntimeException("Cannot initialize AWS SNS: " + e.getMessage(), e);
        }
    }
}
