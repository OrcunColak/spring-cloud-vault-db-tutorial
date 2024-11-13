package com.colak.springtutorial.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("client-credentials")
public class ClientCredentials {

    private String clientId;
    private String clientSecret;
}
