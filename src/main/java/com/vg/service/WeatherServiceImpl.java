package com.vg.service;

import com.vg.service.model.OpenWeatherForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${open-weather-map.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public OpenWeatherForecast getWeatherForecast(String city) {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", city)
                .queryParam("units", "metric")
                .queryParam("appid", apiKey)
                .toUriString();

        try {
            return restTemplate.getForObject(url, OpenWeatherForecast.class);
        } catch (RestClientException e) {
            throw e; //Should have handled client exceptions
        }
    }

}