/* 
User: Urmi
Date: 10/13/2021 
Time: 7:38 AM
*/

package com.in28minutes.microservices.currencyconversionservice.controller;

import com.in28minutes.microservices.currencyconversionservice.client.CurrencyExchangeProxy;
import com.in28minutes.microservices.currencyconversionservice.model.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
                                                          @PathVariable BigDecimal quantity){

        Map<String, String> map = new HashMap<>();
        map.put("from", from);
        map.put("to", to);

        //using docker compose, the localhost:8000 doesn't work, so using service name instead
        /*ResponseEntity<CurrencyConversion> responseEntity =
                new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, map);*/
        ResponseEntity<CurrencyConversion> responseEntity =
                new RestTemplate().getForEntity("http://currency-exchange:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversion.class, map);
        CurrencyConversion currencyConversion =  responseEntity.getBody();
        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment() + " " + "resttemplate");
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to,
                                                          @PathVariable BigDecimal quantity){


        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment() + " " + "feign");
    }
}
