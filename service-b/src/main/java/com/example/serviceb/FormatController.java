package com.example.serviceb;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


// import io.opentracing.Span;
// import io.opentracing.Tracer;

@RestController
public class FormatController {

    @Autowired
    private RestTemplate restTemplate;
    // @Autowired
    // private Tracer tracer;

    @GetMapping("/formatGreeting")
    public String formatGreeting(@RequestParam String name, @RequestHeader HttpHeaders headers) {
        System.out.println("Headers: " + headers);
        // Span span = tracer.scopeManager().activeSpan();
        // span.log("formatting message remotely for name " + name);
        // String response = "Hello, from service-b " + name + "!";
        String response = formatGreetingRemote(name);
        // String myBaggage = span.getBaggageItem("my-baggage");
        // span.log("this is baggage " + myBaggage);
        return response;
    }

    private String formatGreetingRemote(String name) {
        String serviceName = System.getenv("SERVICE_FORMATTER");
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