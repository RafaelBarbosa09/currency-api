package com.example.challengebravo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwesomeAPIConfig {
    @Value("${awesome.api.url}")
    private  String awesomeApiUrl;

    @Bean
    public String getAwesomeApiUrl() {
        return awesomeApiUrl;
    }
}
