package com.eheiker.appdirect.controller.integration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.provider.ConsumerAuthentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eheiker.appdirect.client.AppDirectClient;
import com.eheiker.appdirect.client.action.ActionResult;
import com.eheiker.appdirect.client.action.GetSubscriptionCancelEventAction;
import com.eheiker.appdirect.client.action.GetSubscriptionOrderEventAction;
import com.eheiker.appdirect.client.action.GetUserAssignedEventAction;
import com.eheiker.appdirect.client.action.GetUserUnassignedEventAction;
import com.eheiker.appdirect.domain.appdirect.event.EventResult;
import com.eheiker.appdirect.domain.appdirect.event.access.UserAssignedEvent;
import com.eheiker.appdirect.domain.appdirect.event.access.UserUnassignedEvent;
import com.eheiker.appdirect.domain.appdirect.event.subscription.SubscriptionCancelEvent;
import com.eheiker.appdirect.domain.appdirect.event.subscription.SubscriptionOrderEvent;
import com.eheiker.appdirect.logging.AutowiredLogger;

/**
 * http://info.appdirect.com/developers/docs/api_integration/subscription_management
 */
@RestController
@RequestMapping("/appdirect")
public class AppDirectController {

    @AutowiredLogger
    Logger log;

    @Autowired
    AppDirectClient appDirectClient;

    @RequestMapping(value = "/subscription/create")
    public EventResult createSubscription(HttpServletRequest request,
            @RequestParam String url,
            @RequestParam String token,
            @AuthenticationPrincipal ConsumerAuthentication authentication) {

        logRequest(request, authentication);

        GetSubscriptionOrderEventAction action = new GetSubscriptionOrderEventAction(appDirectClient);
        action.setUrl(url);
        action.setToken(token);
        ActionResult<SubscriptionOrderEvent> actionResult = action.execute();

        //TODO: create an account

        // return result XML
        EventResult result = new EventResult();
        result.setAccountIdentifier("12345");
        result.setMessage("Welcome to AppDirect!");
        result.setSuccess(actionResult.isSuccess());

        return result;
    }

    @RequestMapping("/subscription/cancel")
    public EventResult cancelSubscription(HttpServletRequest request,
            @RequestParam String url,
            @RequestParam String token,
            @AuthenticationPrincipal ConsumerAuthentication authentication) {

        logRequest(request, authentication);

        // get event details
        GetSubscriptionCancelEventAction action = new GetSubscriptionCancelEventAction(appDirectClient);
        action.setUrl(url);
        action.setToken(token);
        SubscriptionCancelEvent event = action.execute().getEntity();

        // return result XML
        EventResult result = new EventResult();
        result.setAccountIdentifier("12345");
        result.setMessage(event.toString());
        result.setSuccess(true);

        return result;
    }

    @RequestMapping("/user/assign")
    public EventResult assignUser(HttpServletRequest request,
            @RequestParam String url,
            @RequestParam String token,
            @AuthenticationPrincipal ConsumerAuthentication authentication) {

        logRequest(request, authentication);

        // get event details
        GetUserAssignedEventAction action = new GetUserAssignedEventAction(appDirectClient);
        action.setUrl(url);
        action.setToken(token);
        UserAssignedEvent event = action.execute().getEntity();

        // return result XML
        EventResult result = new EventResult();
        result.setAccountIdentifier("12345");
        result.setMessage(event.toString());
        result.setSuccess(true);

        return result;
    }

    @RequestMapping("/user/unassign")
    public EventResult unassignUser(HttpServletRequest request,
            @RequestParam String url,
            @RequestParam String token,
            @AuthenticationPrincipal ConsumerAuthentication authentication) {

        logRequest(request, authentication);

        // get event details
        GetUserUnassignedEventAction action = new GetUserUnassignedEventAction(appDirectClient);
        action.setUrl(url);
        action.setToken(token);
        UserUnassignedEvent event = action.execute().getEntity();

        // return result XML
        EventResult result = new EventResult();
        result.setAccountIdentifier("12345");
        result.setMessage(event.toString());
        result.setSuccess(true);

        return result;
    }

    private void logRequest(HttpServletRequest request, ConsumerAuthentication authentication) {
        if (log.isDebugEnabled()) {
            log.debug("request = " + request.getRequestURI() + "?" + request.getQueryString());
            log.debug("authenticated = " + authentication.isAuthenticated());
            log.debug("signature validated = " + authentication.isSignatureValidated());
        }
    }
}
