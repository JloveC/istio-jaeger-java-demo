package com.example.servicea;

import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name, @RequestHeader HttpHeaders headers) {
        System.out.println("Headers: " + headers);
        Map<String, String> fields = new LinkedHashMap<>();
        String response = formatGreetingRemote(name);
        return response;
    }

    private String formatGreetingRemote(String name) {
        String serviceName = System.getenv("SERVICE_B_HOST");
        if (serviceName == null) {
            serviceName = "localhost";
        }
        String urlPath = "http://" + serviceName + ":8080/formatGreeting";
        URI uri = UriComponentsBuilder //
                .fromHttpUrl(urlPath) //
                .queryParam("name", name).build(Collections.emptyMap());
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return response.getBody();
    }

}