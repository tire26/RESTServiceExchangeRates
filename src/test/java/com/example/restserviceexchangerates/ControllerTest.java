package com.example.restserviceexchangerates;


import com.example.restserviceexchangerates.controler.Controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ConcurrentModel;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class ControllerTest {

    @Autowired
    private Controller controller;

    @Test
    public void getCurrentRubRateNotNull() {
        controller.getCurrentRubRate();
        assertFalse(controller.currentRate.isNaN());
    }

    @Test
    public void getYesterdayRubRateNotNull() {
        controller.getYesterdayRubRate();
        assertFalse(controller.yesterdayRate.isNaN());
    }

    @Test
    public void getGifNonNull() {
        controller.getGif(new ConcurrentModel());
        assertFalse(controller.gif.getData().isEmpty());
    }
}
