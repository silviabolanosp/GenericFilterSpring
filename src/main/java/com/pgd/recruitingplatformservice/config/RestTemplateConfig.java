package com.pgd.recruitingplatformservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Web client configuration.
 */
@Configuration
public class RestTemplateConfig {
    /**
     * Definition of Web client.
     * @return Instance of RestTemplate()
     */
    @Bean
    public org.springframework.web.client.RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
