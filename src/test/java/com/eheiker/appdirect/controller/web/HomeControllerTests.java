package com.eheiker.appdirect.controller.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.eheiker.appdirect.fixture.AuthenticationFixtures;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTests {

    MockMvc mockMvc;

    @InjectMocks
    HomeController controller;

    @Before
    public void setup() {
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testUnauthenticated() throws Exception {

        mockMvc.perform(get("/"))
               .andExpect(model().attribute("authenticated", false));
    }

    @Test
    public void testAuthenticated() throws Exception {

        mockMvc.perform(get("/").principal(AuthenticationFixtures.authenticatedOpenIdToken()))
               .andExpect(model().attribute("authenticated", true));
    }

}
