package com.eheiker.appdirect.controller.integration;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth.HttpMethod;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.validators.OAuthValidator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.eheiker.appdirect.domain.appdirect.event.subscription.SubscriptionEventResult;
import com.eheiker.appdirect.domain.appdirect.event.subscription.SubscriptionOrderEvent;
import com.eheiker.appdirect.logging.AutowiredLogger;
import com.eheiker.appdirect.service.appdirect.EventService;
import com.eheiker.appdirect.service.appdirect.SubscriptionService;

/**
 * http://info.appdirect.com/developers/docs/api_integration/subscription_management
 */
@RestController
@RequestMapping("/appdirect/subscription")
public class SubscriptionController {

    @AutowiredLogger
    Logger log;

    @Autowired
    EventService eventService;

    @RequestMapping(value = "/create",
                    method = RequestMethod.GET)
    public SubscriptionEventResult create(HttpServletRequest request, @RequestParam String url, @RequestParam String token) throws OAuthSystemException, OAuthProblemException, JAXBException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        // validate oauth signature: http://info.appdirect.com/developers/docs/api_integration/oauth_api_authentication/

        StringBuilder info = new StringBuilder();
        info.append("requestURI = " + request.getRequestURI() + "\n");
        info.append("queryString = " + request.getQueryString() + "\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            info.append(headerName + ": " + request.getHeader(headerName) + "\n");
        }

        // perform OAuth-signed GET request to url to get event details
        SubscriptionOrderEvent orderEvent = eventService.getEvent(url, token, SubscriptionOrderEvent.class);

        // create an account

        // return result XML
        SubscriptionEventResult result = new SubscriptionEventResult();
        result.setAccountIdentifier("12345");
        result.setMessage(orderEvent.toString());
        result.setSuccess(false);

        return result;
    }

    @RequestMapping("/change")
    public void change() {

    }
    @RequestMapping("/cancel")
    public void cancel() {

    }
    @RequestMapping("/status")
    public void status() {

    }
}
