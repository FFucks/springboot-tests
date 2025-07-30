package com.ffucks.controllers;

import com.ffucks.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return "http://localhost:" + port + "/persons";
    }

    @Test
    void testCreateAndGet() {



        Person p = new Person("Fabio", "5196008487", "fabio_geiss@hotmail.com", address, "1988-12-05");
        ResponseEntity<Person> postResponse = restTemplate.postForEntity(
                baseUrl(), p, Person.class);


        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Person created = postResponse.getBody();
        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();

        ResponseEntity<Person> getResponse = restTemplate.getForEntity(
                baseUrl() + "/" + created.getId(), Person.class);


        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getName()).isEqualTo("Fabio");
    }
}
