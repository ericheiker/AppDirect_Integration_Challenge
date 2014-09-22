package com.eheiker.appdirect.controller.integration;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openid4java.association.AssociationException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.MessageException;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eheiker.appdirect.logging.AutowiredLogger;

@Controller
public class LoginController {

    @AutowiredLogger
    Logger log;

    ConsumerManager consumerManager;

    @RequestMapping("/login")
    public String login(@RequestParam() String openidUrl, @RequestParam String accountId, HttpSession session, HttpServletResponse response) throws DiscoveryException, MessageException, ConsumerException, IOException {

        consumerManager = new ConsumerManager();

        String returnToUrl = "http://appdirect-eheiker.herokuapp.com/validate";

        List<?> discoveries = consumerManager.discover(openidUrl);

        DiscoveryInformation discovered = consumerManager.associate(discoveries);
        log.debug(discovered.toString());
        session.setAttribute("openid-disc", discovered);

        AuthRequest authRequest = consumerManager.authenticate(discovered, returnToUrl);

        String destinationUrl = authRequest.getDestinationUrl(true);

        response.sendRedirect(destinationUrl);

        return "login";
    }

    @RequestMapping("/validate")
    public Identifier validate(HttpServletRequest httpReq) throws AssociationException, DiscoveryException, MessageException {
        // extract the parameters from the authentication response
        // (which comes in as a HTTP request from the OpenID provider)
        ParameterList response =
                new ParameterList(httpReq.getParameterMap());

        // retrieve the previously stored discovery information
        DiscoveryInformation discovered = (DiscoveryInformation)
                httpReq.getSession().getAttribute("openid-disc");

        // extract the receiving URL from the HTTP request
        StringBuffer receivingURL = httpReq.getRequestURL();
        String queryString = httpReq.getQueryString();
        if (queryString != null && queryString.length() > 0)
            receivingURL.append("?").append(httpReq.getQueryString());

        // verify the response; ConsumerManager needs to be the same
        // (static) instance used to place the authentication request
        VerificationResult verification = consumerManager.verify(
                receivingURL.toString(),
                response, discovered);

        // examine the verification result and extract the verified identifier
        Identifier verified = verification.getVerifiedId();
        if (verified != null) {
            AuthSuccess authSuccess =
                    (AuthSuccess) verification.getAuthResponse();

            if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
                FetchResponse fetchResp = (FetchResponse) authSuccess
                        .getExtension(AxMessage.OPENID_NS_AX);

                List emails = fetchResp.getAttributeValues("email");
                String email = (String) emails.get(0);
            }

            return verified;  // success
        }

        return null;
    }
}
