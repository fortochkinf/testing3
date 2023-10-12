package ru.testing.testing3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyConversionService {

    private final ExternalCommunicationService externalCommunicationService;


    public Double convertDollarIntoRoubles(Double amount, Integer samples, String currency){
        double accum = 0.0;
        for (int i = 0; i < samples ; i++) {
            accum += externalCommunicationService.getCurrencyCourse(currency) * amount;
        }
        return accum / samples;
    }

}
