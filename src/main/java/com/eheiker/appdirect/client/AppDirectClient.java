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
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The AppDirectClient handles all communication with AppDirect's APIs. It's primary purpose is to handle the low-level
 * communication details such as transport protocols and marshalling/unmarshalling.
 */
public class AppDirectClient {

    private static Logger log = LoggerFactory.getLogger(AppDirectClient.class);

    private OAuthConsumer oAuthConsumer;

    /**
     * Initialize a new AppDirectClient with the provide OAuth consumer key and secret.
     *
     * @param consumerKey OAuth consumer key
     * @param secret OAuth consumer secret
     * @throws java.lang.IllegalArgumentException if any parameter is null
     */
    public AppDirectClient(String consumerKey, String secret) {
        if (StringUtils.isAnyBlank(consumerKey, secret)) {
            throw new IllegalArgumentException("No parameter can be null");
        }

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
        String signedUrl = signUrl(url);

        log.debug("signed url = " + signedUrl);

        InputStream content = doGet(signedUrl);

        T result = null;

        if (content != null) {

            if (log.isDebugEnabled()) {
                String strContent = IOUtils.toString(content);
                log.debug(strContent);
                content = IOUtils.toInputStream(strContent);
            }

            result = parseResponse(content, resultType);
        } else {
            log.debug("Content from doGet() was null");
        }

        log.debug("result is: " + result);

        return result;
    }

    /**
     * Good candidate to be extracted to its own class
     */
    protected <T> T parseResponse(final InputStream content, final Class<T> resultType) throws JAXBException, IOException {

        T result = null;

        if (resultType.isAnnotationPresent(XmlRootElement.class)) {
            JAXBContext context = JAXBContext.newInstance(resultType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            result = (T) unmarshaller.unmarshal(content);
        } else if (String.class.isAssignableFrom(resultType)) {
            result = (T) IOUtils.toString(content);
        }

        return result;
    }

    /**
     * Good candidate to be extracted to its own class
     */
    protected InputStream doGet(final String signedUrl) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(signedUrl);
        HttpResponse response = client.execute(getRequest);

        HttpEntity entity = response.getEntity();
        return entity.getContent();
    }

    public String signUrl(String url) throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        return oAuthConsumer.sign(url);
    }
}
