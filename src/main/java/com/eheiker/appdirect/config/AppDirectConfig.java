package com.eheiker.appdirect.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eheiker.appdirect.client.AppDirectClient;

@Configuration
public class AppDirectConfig {

    @Value("${APPDIRECT_CONSUMER_KEY}")
    private String consumerKey;

    @Value("${APPDIRECT_SECRET}")
    private String secret;

    @Bean
    public AppDirectClient appDirectClient() {
        AppDirectClient client = new AppDirectClient("appdirect-integration-challenge-14572", "dBwGkwY2R84FAXwS");

        return client;
    }
}
