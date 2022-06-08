package com.example.restserviceexchangerates.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
public class Gif {

    private Map<String, Object> data;

    @JsonCreator
    public Gif(TreeMap<String, Object> data) {
        this.data = data;
    }
    @JsonAnyGetter
    public Map<String, Object> getData() {
        return data;
    }
}
