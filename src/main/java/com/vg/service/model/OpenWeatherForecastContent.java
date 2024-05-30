package com.vg.service.model;

import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherForecastContent {
    private long dt;

    private OpenWeatherForecastMain main;

    private List<OpenWeatherDescription> weather;
}
