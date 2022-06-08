package com.example.restserviceexchangerates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class RestServiceExchangeRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceExchangeRatesApplication.class, args);
	}

}
