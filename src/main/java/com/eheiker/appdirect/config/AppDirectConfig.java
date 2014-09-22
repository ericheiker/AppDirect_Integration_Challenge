package com.eheiker.appdirect.config;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.springframework.context.annotation.Bean;

public class AppDirectConfig {

    @Bean
    public OAuthClient appDirectOAuthClient() {

        OAuthClient client = new OAuthClient(new URLConnectionClient());

        return client;
    }
}
