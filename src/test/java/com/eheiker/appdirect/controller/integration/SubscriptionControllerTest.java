package com.eheiker.appdirect.controller.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.eheiker.appdirect.controller.RestControllerTest;
import com.eheiker.appdirect.domain.appdirect.event.subscription.SubscriptionCancelEvent;
import com.eheiker.appdirect.domain.appdirect.event.subscription.SubscriptionEventResult;
import com.eheiker.appdirect.domain.appdirect.event.subscription.SubscriptionOrderEvent;

public class SubscriptionControllerTest extends RestControllerTest {

    private static List<MediaType> APPLICATION_XML = new ArrayList<MediaType>() {
        {add(MediaType.APPLICATION_XML);}
    };



    @Test
    public void createSuccess() throws JAXBException, IOException {

        SubscriptionOrderEvent event = parseXML("SubscriptionOrderEvent.xml", SubscriptionOrderEvent.class);
        HttpEntity<SubscriptionOrderEvent> requestEntity = createRequestEntity(event, MediaType.APPLICATION_XML);

        ResponseEntity<SubscriptionEventResult> result = postForEntity("/appdirect/subscription/create", requestEntity, SubscriptionEventResult.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        SubscriptionEventResult eventResult = result.getBody();
        assertEquals(true, eventResult.isSuccess());
    }

    @Test
    public void cancelSuccess() throws JAXBException, IOException {
        SubscriptionCancelEvent event = parseXML("SubscriptionCancelEvent.xml", SubscriptionCancelEvent.class);
        HttpEntity<SubscriptionCancelEvent> requestEntity = createRequestEntity(event, MediaType.APPLICATION_XML);

        ResponseEntity<SubscriptionEventResult> result = postForEntity("/appdirect/subscription/cancel", requestEntity, SubscriptionEventResult.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        SubscriptionEventResult eventResult = result.getBody();
        assertEquals(true, eventResult.isSuccess());
    }

    @Test
    public void changeSuccess() {

    }

    @Test
    public void statusSuccess() {

    }
}
