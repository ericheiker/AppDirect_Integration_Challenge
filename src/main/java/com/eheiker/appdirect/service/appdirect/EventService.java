package com.eheiker.appdirect.service.appdirect;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth.HttpMethod;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.stereotype.Service;

import com.eheiker.appdirect.domain.appdirect.event.Event;

@Service
public class EventService {
    public <T extends Event> T getEvent(final String url, final String token, final Class<T> clazz) throws OAuthSystemException, OAuthProblemException, JAXBException {
        OAuthClientRequest clientRequest = new OAuthBearerClientRequest(url).setAccessToken(token)
                                                                            .buildQueryMessage();
        //create OAuth client that uses custom http client under the hood
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        OAuthResourceResponse resource = oAuthClient.resource(clientRequest, HttpMethod.GET, OAuthResourceResponse.class);
        resource.getResponseCode();


        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        T result = (T) unmarshaller.unmarshal(new StringReader(resource.getBody()));

        return result;
    }

}
