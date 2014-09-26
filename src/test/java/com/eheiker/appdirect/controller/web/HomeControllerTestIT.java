package com.eheiker.appdirect.controller.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.eheiker.appdirect.Application;
import com.eheiker.appdirect.fixture.AuthenticationFixtures;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext
public class HomeControllerTestIT {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void testLogoutDisplaysWhenAuthenticated() throws Exception {
        mockMvc.perform(get("/").principal(AuthenticationFixtures.authenticatedOpenIdToken()))
                                  .andExpect(content().string(containsString("logout")));
    }

    @Test
    public void testLogoutNotDisplaysWhenAuthenticated() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(content().string(not(containsString("logout"))));
    }
}
