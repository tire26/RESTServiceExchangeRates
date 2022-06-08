package com.example.restserviceexchangerates;


import com.example.restserviceexchangerates.feignClient.RateFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
public class RateFeignClientTest {

    private final static String CURRENCY = "RUB";
    private final static String YESTERDAY_DATE = "2022-06-07";
    private final static String CURRENT_DATE = "2022-06-08";
    private final static Double CURRENT_RATE = 60.500002;
    private final static Double YESTERDAY_RATE = 61.749995;

    @Autowired
    private RateFeignClient rateFeignClient;

    @Test
    public void gotCurrentRubRateNotEmpty() {
        assertFalse(rateFeignClient.getCurrentRubRate().getRates().isEmpty());
    }


    /**
     * Проверка курса даты 07/06/2022
     */
    @Test
    public void gotYesterdayRateNotEmpty() {
        assertFalse(rateFeignClient.getSpecifiedDateRubRate(YESTERDAY_DATE).getRates().isEmpty());
    }

    /**
     * Проверка курса даты 07/06/2022
     * 61.749995
     */
    @Test
    public void gotYesterdayRateEqualsRightRate() {
        assertEquals(rateFeignClient.getSpecifiedDateRubRate(YESTERDAY_DATE).getRates().get(CURRENCY), YESTERDAY_RATE);
    }
}