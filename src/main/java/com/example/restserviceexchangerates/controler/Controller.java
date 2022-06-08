package com.example.restserviceexchangerates.controler;

import com.example.restserviceexchangerates.feignClient.GIFFeignClient;
import com.example.restserviceexchangerates.feignClient.RateFeignClient;
import com.example.restserviceexchangerates.model.Gif;
import com.example.restserviceexchangerates.model.RateResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;


@RequestMapping("/service")
@org.springframework.stereotype.Controller
public class Controller {

    private final static String CURRENCY = "RUB";
    private final static Long MILLISECONDS_IN_DAY = 86400000L;
    private final static SimpleDateFormat REQUEST_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final RateFeignClient rateFeignClient;
    private final GIFFeignClient gifFeignClient;

    public Double currentRate;
    public Double yesterdayRate;
    public Gif gif;

    public Controller(RateFeignClient rateFeignClient, GIFFeignClient gifFeignClient) {
        this.rateFeignClient = rateFeignClient;
        this.gifFeignClient = gifFeignClient;
    }

    @GetMapping("/getGif")
    public String getGif(Model model) {
        getCurrentRubRate();
        getYesterdayRubRate();
        gif = null;
        if (currentRate > yesterdayRate) {
            gif = gifFeignClient.getBrokeGif();
        } else if (currentRate < yesterdayRate) {
            gif = gifFeignClient.getRichGif();
        }
        assert gif != null;
        Object obj = gif.getData().get("data");
        if (obj != null) {
            LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) obj;
            String url = "https://media4.giphy.com/media/" + data.get("id") + "/giphy.gif";
            model.addAttribute("gif", url);
        }
        return "main";
    }

    public void getCurrentRubRate() {
        Double currentRate = 0.0;
        RateResponse rateResponse;

        rateResponse = rateFeignClient.getCurrentRubRate();

        for (String element:
                rateResponse.getRates().keySet()) {
            if (element.matches(CURRENCY)) {
                currentRate = rateResponse.getRates().get(element);
                System.out.println("Курс рубля к доллару на сегодня: " + rateResponse.getRates().get(element));
                break;
            }
        }
        this.currentRate =  currentRate;
    }

    public void getYesterdayRubRate() {
        Double yesterdayRate = 0.0;
        Date currentDate = new Date();
        RateResponse rateResponse;

        currentDate.setTime(currentDate.getTime() - MILLISECONDS_IN_DAY);
        rateResponse = rateFeignClient.getSpecifiedDateRubRate(REQUEST_DATE_FORMAT.format(currentDate));
        for (String element:
                rateResponse.getRates().keySet()) {
            if (element.matches(CURRENCY)) {
                yesterdayRate = rateResponse.getRates().get(element);
                System.out.println("Курс рубля к доллару на вчера: " + yesterdayRate);
                break;
            }
        }
        this.yesterdayRate =  yesterdayRate;
    }
}
