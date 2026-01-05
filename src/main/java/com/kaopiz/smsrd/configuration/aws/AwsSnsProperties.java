package com.kaopiz.smsrd.configuration.aws;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "aws.sns")
public class AwsSnsProperties {
    private String region;
    private String accessKey;
    private String secretKey;
    private String platformApplicationArn;
    private String endpointOverride;
    private boolean enabled = true;
}
