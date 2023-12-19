package com.example.challengebravo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AwesomeAPIConfig {
    @Value("${awesome.api.url}")
    private  String awesomeApiUrl;

    @Bean
    public String getAwesomeApiUrl() {
        return awesomeApiUrl;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
