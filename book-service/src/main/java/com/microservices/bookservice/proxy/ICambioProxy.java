package com.microservices.bookservice.proxy;

import com.microservices.bookservice.integration.Cambio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cambio-service")
public interface ICambioProxy {

    @GetMapping("/v1/api/cambio-service/{amount}/{from}/{to}")
    Cambio getCambio(@PathVariable("amount")Double amount, @PathVariable("from") String from, @PathVariable("to") String to);
}
