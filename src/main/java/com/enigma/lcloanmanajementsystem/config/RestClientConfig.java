package com.enigma.lcloanmanajementsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {
    @Value("${api.base-url.lms:https://leptosomic-unransomed-anisha.ngrok-free.dev}")
    private String baseUrlLms;

    @Value("${api.connect-timeout-ms:5000}")
    private int connectTimeoutMs;

    @Value("${aapi.connect-timeout-ms:5000}")
    private int readTimeoutMs;

    @Bean
    public RestClient  restClientLms() {
        return RestClient.builder()
                .baseUrl(baseUrlLms)
                .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                .defaultHeader("X-Custom-Header", "RestClient Demo")
                .requestFactory(timeoutConfiguration())
                .build();
    }

    private SimpleClientHttpRequestFactory timeoutConfiguration() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeoutMs);
        factory.setReadTimeout(readTimeoutMs);
        return factory;
    }

}
