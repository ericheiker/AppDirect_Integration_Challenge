package com.eheiker.appdirect.service.appdirect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eheiker.appdirect.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Test
    public void blah() {
        String url = "https://www.appdirect.com/AppDirect/rest/api/events/dummyOrder";

        //eventService.getEvent(url, token)
    }
}
