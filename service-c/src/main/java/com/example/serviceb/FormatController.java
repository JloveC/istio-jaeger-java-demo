package com.example.servicec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FormatController {

    @GetMapping("/formatGreeting")
    public String formatGreeting(@RequestParam String name, @RequestHeader HttpHeaders headers) {
        System.out.println("Headers: " + headers);
        String response = "Hello, from service-c " + name + "!";
        return response;
    }
}