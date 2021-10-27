/* 
User: Urmi
Date: 10/11/2021 
Time: 10:00 PM
*/

package com.in28minutes.microservices.limitsservice.controller;

import com.in28minutes.microservices.limitsservice.configuration.Configuration;
import com.in28minutes.microservices.limitsservice.model.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public Limits retrieveLimits(){
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
