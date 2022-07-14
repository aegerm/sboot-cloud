package com.microservices.cambioservice.service;

import com.microservices.cambioservice.model.Cambio;
import com.microservices.cambioservice.repository.CambioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class CambioService {

    private final CambioRepository cambioRepository;
    private final Environment environment;
    public Cambio getCambio(BigDecimal amount,String from, String to) {
        Cambio cambio = this.cambioRepository.findByFromAndTo(from, to);
        if (cambio == null) {
            throw new RuntimeException("Currency Unsupported");
        }

        String port = this.environment.getProperty("local.server.port");
        BigDecimal conversionFactor = cambio.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount);

        cambio.setEnvironment(port);
        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));

        return cambio;
    }
}
