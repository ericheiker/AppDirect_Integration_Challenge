package com.eheiker.appdirect.controller;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApiControllerTest extends RestControllerTest {

    @Test
    public void testHome() throws Exception {
        ResponseEntity<String> entity = getForEntity("/api/hello", String.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue("Wrong body: " + entity.getBody(), entity.getBody().contains("world"));
    }
}
