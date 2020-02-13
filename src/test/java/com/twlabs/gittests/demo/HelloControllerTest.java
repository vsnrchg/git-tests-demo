package com.twlabs.gittests.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HelloControllerTest {
    @Autowired
    HelloController helloController;
    @Test
    public void testSayHello(){
        String result = helloController.echoHello("World");
        assertEquals("Hello World", result);
    }
}
