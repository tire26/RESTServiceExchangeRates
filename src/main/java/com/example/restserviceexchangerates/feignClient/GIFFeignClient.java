package com.example.restserviceexchangerates.feignClient;

import com.example.restserviceexchangerates.model.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "gif", url = "https://api.giphy.com/v1/gifs")
public interface GIFFeignClient {

    @GetMapping("/random?api_key=AW039Px9JZVhqQJVPN9CnEsKc2VlfS78&tag=rich&rating=g")
    Gif getRichGif();

    @GetMapping("/random?api_key=AW039Px9JZVhqQJVPN9CnEsKc2VlfS78&tag=broke&rating=g")
    Gif getBrokeGif();
}
