package com.vg.model;

import lombok.Data;

@Data
public class WeatherDataForecastHourly {
    private String description;

    private String hour;

    private double temperature;

    private double min_temperature;

    private double max_temperature;
}
