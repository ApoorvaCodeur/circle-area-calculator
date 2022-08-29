package com.example.circleareacalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CircleAreaCalculatorApplicationTests {

    private URL base;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/circle/area");
    }

    @Test
    public void testCalculateAreaSuccess() {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/5", String.class);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCalculateAreaInvalidRadius() {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/-1", String.class);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCalculateAreaFailure() {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "/xyz", String.class);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
