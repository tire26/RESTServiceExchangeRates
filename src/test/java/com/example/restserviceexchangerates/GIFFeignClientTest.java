package com.example.restserviceexchangerates;

import com.example.restserviceexchangerates.feignClient.GIFFeignClient;
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
public class GIFFeignClientTest {

    @Autowired
    private GIFFeignClient gifFeignClient;

    @Test
    public void gotRichGifNotNull() {
        assertFalse(gifFeignClient.getRichGif().getData().isEmpty());
    }

    @Test
    public void gotBrokeGifNotNull() {
        assertFalse(gifFeignClient.getBrokeGif().getData().isEmpty());
    }
}
