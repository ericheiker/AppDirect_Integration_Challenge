package com.eheiker.appdirect.controller.integration;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eheiker.appdirect.controller.RestControllerTestIT;
import com.eheiker.appdirect.domain.appdirect.event.EventResult;

import static org.junit.Assert.assertEquals;

public class SubscriptionControllerTestsIT extends RestControllerTestIT {

    @Test
    public void createSuccess() throws JAXBException, IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {

        String url = constructUrl("/test/event/createSuccess");

        ResponseEntity<EventResult> result = getForEntity("/appdirect/subscription/create?url=" + url + "&token=test", EventResult.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        EventResult eventResult = result.getBody();
        assertEquals(true, eventResult.isSuccess());
    }
}
