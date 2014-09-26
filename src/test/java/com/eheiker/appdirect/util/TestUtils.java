package com.eheiker.appdirect.util;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class TestUtils {
    public static <T> T parseXML(ApplicationContext context, String classPath, Class<T> classToBind) throws JAXBException, IOException {
        Resource resource = context.getResource("classpath:" + classPath);

        JAXBContext jaxbContext = JAXBContext.newInstance(classToBind);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        T result = (T) unmarshaller.unmarshal(resource.getInputStream());

        return result;
    }

    public static <T> HttpEntity<T> createRequestEntity(T object, final MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(mediaType);
        headers.setAccept(new ArrayList<MediaType>() {
            {add(mediaType);}
        });

        return new HttpEntity<T>(object, headers);
    }
}
