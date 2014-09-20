package com.eheiker.appdirect.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.eheiker.appdirect.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext
public abstract class RestControllerTest {

    @Value("${local.server.port}")
    private int port;

    protected <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType) {
        return new TestRestTemplate().getForEntity(
                "http://localhost:" + this.port + "/" + url, responseType);
    }
}
