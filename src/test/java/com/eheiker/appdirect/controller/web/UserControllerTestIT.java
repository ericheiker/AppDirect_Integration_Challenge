package com.eheiker.appdirect.controller.web;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.eheiker.appdirect.Application;
import com.eheiker.appdirect.domain.myapp.Profile;
import com.eheiker.appdirect.service.ProfileService;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext
public class UserControllerTestIT {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @Mock
    ProfileService profileService;

    @Autowired
    UserController controller;

    List<Profile> users;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = webAppContextSetup(context).build();

        ReflectionTestUtils.setField(controller, "profileService", profileService);

        users = new ArrayList<>();
        Profile user1 = new Profile();
        user1.setId(1L);
        user1.setFirstName("Eric");
        user1.setLastName("Heiker");
        user1.setEmail("eric.heiker@fake.com");
        users.add(user1);
    }

    @Test
    public void testUsers() throws Exception {
        when(profileService.getAll()).thenReturn(users);

        mockMvc.perform(get("/users"))
            .andExpect(content().string(containsString("Heiker")));
    }

    @Test
    public void testNoUsers() throws Exception {
        mockMvc.perform(get("/users"))
            .andExpect(content().string(containsString("Nada here")));
    }

}
