package com.eheiker.appdirect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// https://github.com/spring-projects/spring-boot/blob/master/spring-boot-samples/spring-boot-sample-actuator-log4j/src/test/java/sample/actuator/log4j/SampleActuatorApplicationTests.java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@DirtiesContext
public class ApplicationTest {

    @Test
    public void test() {

    }
}
