package com.eheiker.appdirect.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.eheiker.appdirect.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext
public abstract class RestControllerTest {

    @Autowired
    protected ApplicationContext appContext;

    @Value("${local.server.port}")
    private int port;

    protected <T> ResponseEntity<T> getForEntity(String path, Class<T> responseType) {
        return new TestRestTemplate().getForEntity(constructUrl(path), responseType);
    }

    protected <T> ResponseEntity<T> postForEntity(String path, Object request, Class<T> responseType) {
        return new TestRestTemplate().postForEntity(constructUrl(path), request, responseType);
    }

    private String constructUrl(String path) {
        return new StringBuilder("http://localhost:").append(this.port).append("/").append(path).toString();
    }

    protected <T> T parseXML(String filename, Class<T> classToBind) throws JAXBException, IOException {
        Resource resource = appContext.getResource("classpath:" + filename);

        JAXBContext context = JAXBContext.newInstance(classToBind);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        T result = (T) unmarshaller.unmarshal(resource.getInputStream());

        return result;
    }

    protected <T> HttpEntity<T> createRequestEntity(T object, final MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(mediaType);
        headers.setAccept(new ArrayList<MediaType>() {
            {add(mediaType);}
        });

        return new HttpEntity<T>(object, headers);
    }
}
