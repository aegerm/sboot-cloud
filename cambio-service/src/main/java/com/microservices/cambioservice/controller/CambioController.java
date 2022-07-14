package com.microservices.cambioservice.controller;

import com.microservices.cambioservice.model.Cambio;
import com.microservices.cambioservice.service.CambioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/api/cambio-service")
@RequiredArgsConstructor
public class CambioController {

    private final CambioService cambioService;

    @GetMapping("/{amount}/{from}/{to}")
    public ResponseEntity<Cambio> getCambioResponseEntity(@PathVariable BigDecimal amount, @PathVariable String from, @PathVariable String to) {
        Cambio cambio = this.cambioService.getCambio(amount, from, to);
        return ResponseEntity.ok(cambio);
    }
}
