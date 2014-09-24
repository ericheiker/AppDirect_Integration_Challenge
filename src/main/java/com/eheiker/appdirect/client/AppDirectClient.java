package com.eheiker.appdirect.client;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppDirectClient {

    private static Logger log = LoggerFactory.getLogger(AppDirectClient.class);

    private String consumerKey;
    private String secret;

    private OAuthConsumer oAuthConsumer;

    public AppDirectClient(String consumerKey, String secret) {
        this.consumerKey = consumerKey;
        this.secret = secret;

        this.oAuthConsumer = new DefaultOAuthConsumer(consumerKey, secret);
    }

    /**
     * Signs a URL using client's OAuth credentials and performs a GET request
     *
     * @param url the unsigned URL
     * @param resultType the result type returned
     *
     * @return the result
     *
     * @throws OAuthCommunicationException
     * @throws OAuthExpectationFailedException
     * @throws OAuthMessageSignerException
     * @throws IOException
     * @throws JAXBException
     */
    public <T> T signAndGet(String url, Class<T> resultType) throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException, IOException, JAXBException {

        T result = null;

        String signedUrl = signUrl(url);

        log.debug("signed url = " + signedUrl);

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(signedUrl);
        HttpResponse response = client.execute(getRequest);

        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();

        if (log.isDebugEnabled()) {
            String strContent = IOUtils.toString(content);
            log.debug(strContent);
            content = IOUtils.toInputStream(strContent);
        }

        if (resultType.isAnnotationPresent(XmlRootElement.class)) {
            JAXBContext context = JAXBContext.newInstance(resultType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            result = (T) unmarshaller.unmarshal(content);
        } else if (String.class.isAssignableFrom(resultType)) {
            result = (T) IOUtils.toString(content);
        }

        log.debug(result.toString());

        return result;
    }

    private String signUrl(String url) throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        return oAuthConsumer.sign(url);
    }
}
