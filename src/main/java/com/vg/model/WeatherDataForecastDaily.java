package com.vg.model;

import lombok.Data;

import java.util.List;

@Data
public class WeatherDataForecastDaily {
    private boolean alertUser;

    private String dt;

    private List<WeatherDataForecastHourly> hourly;

}
