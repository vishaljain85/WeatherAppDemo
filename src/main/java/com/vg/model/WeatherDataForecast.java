package com.vg.model;

import lombok.Data;

import java.util.List;

@Data
public class WeatherDataForecast {
    private double maxTempAlert;

    private List<WeatherDataForecastDaily> daily;
}
