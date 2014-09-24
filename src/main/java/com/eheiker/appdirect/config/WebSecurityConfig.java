package com.eheiker.appdirect.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.eheiker.appdirect.security.CustomUserDetailsService;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .csrf().disable();
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
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
