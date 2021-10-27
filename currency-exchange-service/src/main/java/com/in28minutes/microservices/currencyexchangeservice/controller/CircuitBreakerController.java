/* 
User: Urmi
Date: 10/14/2021 
Time: 6:17 PM
*/

package com.in28minutes.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
    @CircuitBreaker(name = "sample-api", fallbackMethod = "hardCodedResponse")
    @RateLimiter(name = "default")
    public String circuitBreak(){
        logger.info("Sample API call received");
        ResponseEntity<String> res = new RestTemplate().getForEntity("http://localhost:8080/dummy", String.class);
        return res.getBody();
    }

    public String hardCodedResponse(Exception e){
        return "fallback response";
    }

}
