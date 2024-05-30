package com.vg.controller.builder;

import com.github.javafaker.Faker;
import com.vg.service.model.OpenWeatherDescription;
import com.vg.service.model.OpenWeatherForecastContent;
import com.vg.service.model.OpenWeatherForecastMain;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OpenWeatherForecastContentBuilder {
    private long dt;
    private OpenWeatherForecastMain main;
    private List<OpenWeatherDescription> weather;
    private static final Faker faker = new Faker();

    public OpenWeatherForecastContentBuilder() {
        this.dt = faker.date().future(10, TimeUnit.DAYS).getTime();
        this.main = new OpenWeatherForecastMainBuilder().build();
        this.weather = IntStream.range(0, faker.number().numberBetween(1, 5))
                .mapToObj(i -> new OpenWeatherDescriptionBuilder().build())
                .collect(Collectors.toList());
    }

    public OpenWeatherForecastContentBuilder withDt(long dt) {
        this.dt = dt;
        return this;
    }

    public OpenWeatherForecastContentBuilder withMain(OpenWeatherForecastMain main) {
        this.main = main;
        return this;
    }

    public OpenWeatherForecastContentBuilder withWeather(List<OpenWeatherDescription> weather) {
        this.weather = weather;
        return this;
    }

    public OpenWeatherForecastContent build() {
        OpenWeatherForecastContent openWeatherForecastContent = new OpenWeatherForecastContent();
        openWeatherForecastContent.setDt(this.dt);
        openWeatherForecastContent.setMain(this.main);
        openWeatherForecastContent.setWeather(this.weather);
        return openWeatherForecastContent;
    }
}

