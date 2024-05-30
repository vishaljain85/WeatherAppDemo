package com.vg.service.model;

import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherForecast {
    private int cnt;

    private List<OpenWeatherForecastContent> list;
}
