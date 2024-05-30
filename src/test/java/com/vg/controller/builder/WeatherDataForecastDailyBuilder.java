package com.vg.controller.builder;

import com.github.javafaker.Faker;
import com.vg.model.WeatherDataForecastDaily;
import com.vg.model.WeatherDataForecastHourly;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WeatherDataForecastDailyBuilder {
    private boolean alertUser;
    private String dt;
    private List<WeatherDataForecastHourly> hourly;
    private static final Faker faker = new Faker();

    public WeatherDataForecastDailyBuilder() {
        this.alertUser = faker.bool().bool();
        this.dt = faker.date().toString();
        this.hourly = IntStream.range(0, faker.number().numberBetween(1, 6))
                .mapToObj(i -> new WeatherDataForecastHourlyBuilder().build())
                .collect(Collectors.toList());
    }

    public WeatherDataForecastDailyBuilder withAlertUser(boolean alertUser) {
        this.alertUser = alertUser;
        return this;
    }

    public WeatherDataForecastDailyBuilder withDt(String dt) {
        this.dt = dt;
        return this;
    }

    public WeatherDataForecastDailyBuilder withHourly(List<WeatherDataForecastHourly> hourly) {
        this.hourly = hourly;
        return this;
    }

    public WeatherDataForecastDaily build() {
        WeatherDataForecastDaily weatherDataForecastDaily = new WeatherDataForecastDaily();
        weatherDataForecastDaily.setAlertUser(this.alertUser);
        weatherDataForecastDaily.setDt(this.dt);
        weatherDataForecastDaily.setHourly(this.hourly);
        return weatherDataForecastDaily;
    }
}


