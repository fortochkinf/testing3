package ru.testing.testing3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExternalCommunicationService {

    private final RestTemplate restTemplate;

    public Double getCurrencyCourse(String currency){

        if (currency.equals("euro")){
            return getEuroCourse();
        }else if (currency.equals("dollar")){
            return getDollarCourse();
        }

        throw new IllegalArgumentException("Unknown currency " + currency);
    }

    private Double getDollarCourse(){
        return Double.valueOf(Objects.requireNonNull(restTemplate.getForEntity("http://american/", String.class).getBody()));
    }

    private Double getEuroCourse(){
        return Double.valueOf(Objects.requireNonNull(restTemplate.getForEntity("http://eurobank/", String.class).getBody()));
    }

}
