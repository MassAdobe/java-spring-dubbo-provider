package com.massadobe.attempt.application.contoller;

import com.massadobe.attempt.application.AttemptApplication;
import com.massadobe.attempt.application.service.UserInterface;
import com.massadobe.provider.DubboUserInterface;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AttemptApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        System.out.println(String.format("port is : [%d]", port));
    }

    @Autowired
    private UserInterface userInterface;

    @Reference
    private DubboUserInterface dubboUserInterface;

    @Test
    public void Master() {
        Assert.assertNotNull(this.testRestTemplate.getForObject("http://localhost:" + port + "/hello/read", String.class));
    }

}
