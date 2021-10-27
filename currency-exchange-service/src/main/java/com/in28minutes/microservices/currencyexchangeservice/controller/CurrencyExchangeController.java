/* 
User: Urmi
Date: 10/12/2021 
Time: 8:38 PM
*/

package com.in28minutes.microservices.currencyexchangeservice.controller;

import com.in28minutes.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.in28minutes.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private Environment env;

    @Autowired
    private CurrencyExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        logger.info("retrieveExchangeValue called with {} and {}", from, to);
        //CurrencyExchange exchange =  new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(75L));
        CurrencyExchange exchange = repository.findByFromAndTo(from, to);
        if(exchange == null){
            throw new RuntimeException("No data found for from " + from + " to " + to);
        }
        exchange.setEnvironment(env.getProperty("local.server.port"));
        return exchange;

    }
}
