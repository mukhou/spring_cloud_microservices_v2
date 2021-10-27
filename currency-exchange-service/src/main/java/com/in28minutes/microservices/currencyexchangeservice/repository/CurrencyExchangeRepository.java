/* 
User: Urmi
Date: 10/12/2021 
Time: 9:48 PM
*/

package com.in28minutes.microservices.currencyexchangeservice.repository;

import com.in28minutes.microservices.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    CurrencyExchange findByFromAndTo(String from, String to);

}
