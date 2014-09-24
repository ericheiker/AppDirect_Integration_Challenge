package com.eheiker.appdirect.controller.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.eheiker.appdirect.controller.RestControllerTest;
import com.eheiker.appdirect.domain.appdirect.event.EventResult;
import com.eheiker.appdirect.domain.appdirect.event.subscription.SubscriptionCancelEvent;
import com.eheiker.appdirect.domain.appdirect.event.subscription.SubscriptionOrderEvent;

public class SubscriptionControllerTest extends RestControllerTest {

    private static List<MediaType> APPLICATION_XML = new ArrayList<MediaType>() {
        {add(MediaType.APPLICATION_XML);}
    };



    @Test
    public void createSuccess() throws JAXBException, IOException {

        SubscriptionOrderEvent event = parseXML("SubscriptionOrderEvent.xml", SubscriptionOrderEvent.class);
        HttpEntity<SubscriptionOrderEvent> requestEntity = createRequestEntity(event, MediaType.APPLICATION_XML);

        ResponseEntity<EventResult> result = postForEntity("/appdirect/subscription/create", requestEntity, EventResult.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        EventResult eventResult = result.getBody();
        assertEquals(true, eventResult.isSuccess());
    }

    @Test
    public void cancelSuccess() throws JAXBException, IOException {
        SubscriptionCancelEvent event = parseXML("SubscriptionCancelEvent.xml", SubscriptionCancelEvent.class);
        HttpEntity<SubscriptionCancelEvent> requestEntity = createRequestEntity(event, MediaType.APPLICATION_XML);

        ResponseEntity<EventResult> result = postForEntity("/appdirect/subscription/cancel", requestEntity, EventResult.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        EventResult eventResult = result.getBody();
        assertEquals(true, eventResult.isSuccess());
    }

    @Test
    public void changeSuccess() {

    }

    @Test
    public void statusSuccess() {

    }
}
