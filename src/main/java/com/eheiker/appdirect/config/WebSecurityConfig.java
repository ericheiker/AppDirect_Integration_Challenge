package com.eheiker.appdirect.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.InMemoryConsumerDetailsService;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.token.InMemoryProviderTokenServices;
import org.springframework.security.oauth.provider.token.OAuthProviderTokenServices;
import org.springframework.security.openid.OpenIDAuthenticationFilter;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.eheiker.appdirect.security.CustomProtectedResourceProcessingFilter;
import com.eheiker.appdirect.security.CustomUserDetailsService;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${APPDIRECT_CONSUMER_KEY}")
    private String consumerKey;

    @Value("${APPDIRECT_SECRET}")
    private String secret;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .csrf().disable();
        http
            .authorizeRequests()
                .antMatchers("/", "/home", "/appdirect/**").permitAll()
                .anyRequest().authenticated();
        http
            .openidLogin()
                .permitAll()
                .authenticationUserDetailsService(new CustomUserDetailsService())
                .attributeExchange("https://www.appdirect.com.*")
                    .attribute("email")
                        .type("http://axschema.org/contact/email")
                        .required(true)
                        .and()
                    .attribute("firstname")
                        .type("http://axschema.org/namePerson/first")
                        .required(true)
                        .and()
                    .attribute("lastname")
                        .type("http://axschema.org/namePerson/last")
                        .required(true);
        http
            .logout()
                .logoutSuccessHandler(new CustomLogoutSuccessHandler());
        http
            .addFilterAfter(oAuthProviderProcessingFilter(), OpenIDAuthenticationFilter.class);
    }

    @Bean
    OAuthProviderProcessingFilter oAuthProviderProcessingFilter() {
        List<RequestMatcher> requestMatchers = new ArrayList<>();
        requestMatchers.add(new AntPathRequestMatcher("/appdirect/**"));
        ProtectedResourceProcessingFilter filter = new CustomProtectedResourceProcessingFilter(requestMatchers);

        filter.setConsumerDetailsService(consumerDetailsService());
        filter.setTokenServices(providerTokenServices());

        return filter;
    }

    @Bean
    public ConsumerDetailsService consumerDetailsService() {
        InMemoryConsumerDetailsService consumerDetailsService = new InMemoryConsumerDetailsService();

        BaseConsumerDetails consumerDetails = new BaseConsumerDetails();
        consumerDetails.setConsumerKey(consumerKey);
        consumerDetails.setSignatureSecret(new SharedConsumerSecretImpl(secret));
        consumerDetails.setRequiredToObtainAuthenticatedToken(false);

        Map<String, BaseConsumerDetails> consumerDetailsStore = new HashMap<>();
        consumerDetailsStore.put(consumerKey, consumerDetails);

        consumerDetailsService.setConsumerDetailsStore(consumerDetailsStore);

        return consumerDetailsService;
    }

    @Bean
    public OAuthProviderTokenServices providerTokenServices() {
        return new InMemoryProviderTokenServices();
    }


    private class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
        @Override
        public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {
            if (authentication instanceof OpenIDAuthenticationToken) {
                OpenIDAuthenticationToken openIDToken = (OpenIDAuthenticationToken) authentication;
                response.sendRedirect("https://www.appdirect.com/applogout?openid=" + openIDToken.getIdentityUrl());
            } else {
                response.sendRedirect("/?logout");
            }

            return;
        }
    }

}
