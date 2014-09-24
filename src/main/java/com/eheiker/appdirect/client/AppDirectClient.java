package com.eheiker.appdirect.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

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

public class AppDirectClient {

    private String consumerKey;
    private String secret;

    public AppDirectClient(String consumerKey, String secret) {
        this.consumerKey = consumerKey;
        this.secret = secret;
    }

    public OAuthConsumer createOAuthConsumer() {
        return new DefaultOAuthConsumer(consumerKey, secret);
    }

    public <T> T signAndGet(String url, Class<T> resultType) {

        T result = null;
        try {
            String signedUrl = signUrl(url);

            //log.debug("signed url = " + signedUrl);

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet getRequest = new HttpGet(signedUrl);
            HttpResponse response = client.execute(getRequest);

            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
/*
            if (log.isDebugEnabled()) {
                String strContent = IOUtils.toString(content);
                log.debug(strContent);
                content = IOUtils.toInputStream(strContent);
            }*/

            if (resultType.isAnnotationPresent(XmlRootElement.class)) {
                JAXBContext context = JAXBContext.newInstance(resultType);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                result = (T) unmarshaller.unmarshal(content);
            } else if (String.class.isAssignableFrom(resultType)) {
                result = (T) IOUtils.toString(content);
            }

        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        //log.debug(result.toString());

        return result;
    }

    private String signUrl(String url) throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        OAuthConsumer consumer = createOAuthConsumer();

        return consumer.sign(url);
    }
}
