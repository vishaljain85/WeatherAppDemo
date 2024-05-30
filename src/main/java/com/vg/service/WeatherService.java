package com.vg.service;

import com.vg.service.model.OpenWeatherForecast;

public interface WeatherService {
    OpenWeatherForecast getWeatherForecast(String city);
}
