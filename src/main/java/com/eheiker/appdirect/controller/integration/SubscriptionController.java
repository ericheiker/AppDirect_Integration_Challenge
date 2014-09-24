package com.eheiker.appdirect.controller.integration;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eheiker.appdirect.client.AppDirectClient;
import com.eheiker.appdirect.client.action.ActionResult;
import com.eheiker.appdirect.client.action.GetSubscriptionCancelEventAction;
import com.eheiker.appdirect.client.action.GetSubscriptionOrderEventAction;
import com.eheiker.appdirect.domain.appdirect.event.subscription.SubscriptionCancelEvent;
import com.eheiker.appdirect.domain.appdirect.event.subscription.SubscriptionEventResult;
import com.eheiker.appdirect.domain.appdirect.event.subscription.SubscriptionOrderEvent;
import com.eheiker.appdirect.logging.AutowiredLogger;

/**
 * http://info.appdirect.com/developers/docs/api_integration/subscription_management
 */
@RestController
@RequestMapping("/appdirect/subscription")
public class SubscriptionController {

    @AutowiredLogger
    Logger log;

    @Autowired
    AppDirectClient appDirectClient;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public SubscriptionEventResult create(HttpServletRequest request, @RequestParam String url, @RequestParam String token) throws OAuthSystemException, OAuthProblemException, JAXBException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        // validate oauth signature: http://info.appdirect.com/developers/docs/api_integration/oauth_api_authentication/

        GetSubscriptionOrderEventAction action = new GetSubscriptionOrderEventAction(appDirectClient);
        action.setUrl(url);
        action.setToken(token);
        SubscriptionOrderEvent event = action.execute().getEntity();

        // create an account
            // create user from event
            // userService.create();

        // return result XML
        SubscriptionEventResult result = new SubscriptionEventResult();
        result.setAccountIdentifier("12345");
        result.setMessage(event.toString());
        result.setSuccess(true);

        return result;
    }

    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public SubscriptionEventResult change(@RequestParam String url, @RequestParam String token) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthSystemException, JAXBException, OAuthProblemException, OAuthCommunicationException, IOException {
        // validate oauth signature: http://info.appdirect.com/developers/docs/api_integration/oauth_api_authentication/

        // perform OAuth-signed GET request to url to get event details

        // create an account

        // return result XML
        SubscriptionEventResult result = new SubscriptionEventResult();
        result.setAccountIdentifier("12345");
        //result.setMessage(orderEvent.toString());
        result.setSuccess(true);

        return result;
    }
    @RequestMapping("/cancel")
    public SubscriptionEventResult cancel(@RequestParam String url, @RequestParam String token) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthSystemException, JAXBException, OAuthProblemException, OAuthCommunicationException, IOException {
        // validate oauth signature: http://info.appdirect.com/developers/docs/api_integration/oauth_api_authentication/

        // perform OAuth-signed GET request to url to get event details
        GetSubscriptionCancelEventAction action = new GetSubscriptionCancelEventAction(appDirectClient);
        action.setUrl(url);
        action.setToken(token);
        SubscriptionCancelEvent event = action.execute().getEntity();

        // create an account

        // return result XML
        SubscriptionEventResult result = new SubscriptionEventResult();
        result.setAccountIdentifier("12345");
        result.setMessage(event.toString());
        result.setSuccess(true);

        return result;
    }
    @RequestMapping("/status")
    public SubscriptionEventResult status(@RequestParam String url, @RequestParam String token) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthSystemException, JAXBException, OAuthProblemException, OAuthCommunicationException, IOException {
        // validate oauth signature: http://info.appdirect.com/developers/docs/api_integration/oauth_api_authentication/

        // perform OAuth-signed GET request to url to get event details


        // create an account

        // return result XML
        SubscriptionEventResult result = new SubscriptionEventResult();
        result.setAccountIdentifier("12345");
        //result.setMessage(orderEvent.toString());
        result.setSuccess(true);

        return result;
    }
}
