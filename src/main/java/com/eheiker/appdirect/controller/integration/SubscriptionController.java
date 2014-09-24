package com.eheiker.appdirect.controller.integration;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpRequest;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.parameters.OAuthParametersApplier;
import org.glassfish.jersey.oauth1.signature.HmaSha1Method;
import org.glassfish.jersey.oauth1.signature.OAuth1Parameters;
import org.glassfish.jersey.oauth1.signature.OAuth1Request;
import org.glassfish.jersey.oauth1.signature.OAuth1Secrets;
import org.glassfish.jersey.oauth1.signature.OAuth1Signature;
import org.glassfish.jersey.oauth1.signature.OAuth1SignatureMethod;
import org.glassfish.jersey.server.oauth1.internal.OAuthServerRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.client.CoreOAuthConsumerSupport;
import org.springframework.security.oauth.provider.filter.CoreOAuthProviderSupport;
import org.springframework.util.StringUtils;
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

    @RequestMapping(value = "/create")
    public SubscriptionEventResult create(HttpServletRequest request, @RequestParam String url, @RequestParam String token) throws OAuthSystemException, OAuthProblemException, JAXBException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        if (log.isDebugEnabled()) {
            CoreOAuthProviderSupport support = new CoreOAuthProviderSupport();
            Map<String, String> oAuthParameters = support.parseParameters(request);

            StringBuilder sb = new StringBuilder();
            sb.append(request.getRequestURI()).append("?").append(request.getQueryString());
            for (Entry<String, String> entry : oAuthParameters.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }

            log.debug(sb.toString());
        }

        GetSubscriptionOrderEventAction action = new GetSubscriptionOrderEventAction(appDirectClient);
        action.setUrl(url);
        action.setToken(token);
        ActionResult<SubscriptionOrderEvent> actionResult = action.execute();

        //TODO: create an account

        // return result XML
        SubscriptionEventResult result = new SubscriptionEventResult();
        result.setAccountIdentifier("12345");
        result.setMessage("Welcome to AppDirect!");
        result.setSuccess(actionResult.isSuccess());

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
}
