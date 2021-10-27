/* 
User: Urmi
Date: 10/13/2021 
Time: 8:08 AM
*/

package com.in28minutes.microservices.currencyconversionservice.client;

import com.in28minutes.microservices.currencyconversionservice.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Without using Eureka
//@FeignClient(name = "currency-exchange", url = "localhost:8000")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
