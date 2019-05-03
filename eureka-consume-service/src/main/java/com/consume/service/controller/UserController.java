package com.consume.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value("${user.userServiceUrl}")
    private String userServiceUrl;

    @GetMapping(value = "/user")
    public String user() {
        System.out.println("===============================/user===================================");
        return restTemplate.getForEntity("http://"+userServiceUrl+"/user", String.class).getBody();
    }

    @GetMapping(value = "/host")
    public String host() {
        System.out.println("===============================/host===================================");
        return restTemplate.getForEntity("http://"+userServiceUrl+"/host", String.class).getBody();
    }

    @GetMapping(value = "/instance")
    public String logUserInstance() {
        System.out.println("===============================/instance===================================");
        return restTemplate.getForEntity("http://"+userServiceUrl+"/user-instance", String.class).getBody();
    }
}
