package com.example.restserviceexchangerates.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class RateResponse {

    String disclaimer;

    private final String license;

    private final String timestamp;

    private final String base;

    private final HashMap<String, Double> rates;

    @JsonCreator
    public RateResponse(String disclaimer, String license, String timestamp, String base, HashMap<String, Double> rates) {
        this.disclaimer = disclaimer;
        this.license = license;
        this.timestamp = timestamp;
        this.base = base;
        this.rates = rates;
    }
}
