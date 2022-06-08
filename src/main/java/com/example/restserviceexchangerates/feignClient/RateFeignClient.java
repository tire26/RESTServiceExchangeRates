package com.example.restserviceexchangerates.feignClient;

import com.example.restserviceexchangerates.model.RateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rate", url = "https://openexchangerates.org/api")
public interface RateFeignClient {

    @GetMapping("/latest.json?app_id=e2135cc066b84f3bb6615fc72ef190e0")
    RateResponse getCurrentRubRate();

    @GetMapping("/historical/{date}.json?app_id=e2135cc066b84f3bb6615fc72ef190e0")
    RateResponse getSpecifiedDateRubRate(@PathVariable String date);
}
