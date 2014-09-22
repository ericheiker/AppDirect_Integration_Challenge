package com.eheiker.appdirect.service.appdirect;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth.HttpMethod;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.eheiker.appdirect.domain.appdirect.event.Event;
import com.eheiker.appdirect.logging.AutowiredLogger;

@Service
public class EventService {

    @AutowiredLogger
    private Logger log;

    public <T extends Event> T getEvent(final String url, final String token, final Class<T> clazz) throws OAuthSystemException, OAuthProblemException, JAXBException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException, IOException {

        String consumerKey = "appdirect-integration-challenge-14572";
        String secret = "dBwGkwY2R84FAXwS";

        OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, secret);
        String signedUrl = consumer.sign(url);

        log.debug("signed url = " + signedUrl);

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(signedUrl);
        HttpResponse response = client.execute(getRequest);

        HttpEntity entity = response.getEntity();

        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        T result = (T) unmarshaller.unmarshal(entity.getContent());

        log.debug(result.toString());

        return result;
    }

}
