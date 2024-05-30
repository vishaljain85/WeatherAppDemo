package com.vg.controller.builder;

import com.github.javafaker.Faker;
import com.vg.model.WeatherDataForecast;
import com.vg.model.WeatherDataForecastDaily;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WeatherDataForecastBuilder {
    private double maxTempAlert;
    private List<WeatherDataForecastDaily> daily;

    private static final Faker faker = new Faker();

    public WeatherDataForecastBuilder() {
        this.maxTempAlert = faker.number().randomDouble(2, 0, 50);
        this.daily = IntStream.range(0, faker.number().numberBetween(1, 5))
                .mapToObj(i -> new WeatherDataForecastDailyBuilder().build())
                .collect(Collectors.toList());
    }

    public WeatherDataForecastBuilder withMaxTempAlert(double maxTempAlert) {
        this.maxTempAlert = maxTempAlert;
        return this;
    }

    public WeatherDataForecastBuilder withDaily(List<WeatherDataForecastDaily> daily) {
        this.daily = daily;
        return this;
    }

    public WeatherDataForecastBuilder addDaily(WeatherDataForecastDaily dailyData) {
        this.daily.add(dailyData);
        return this;
    }

    public WeatherDataForecast build() {
        WeatherDataForecast weatherDataForecast = new WeatherDataForecast();
        weatherDataForecast.setMaxTempAlert(this.maxTempAlert);
        weatherDataForecast.setDaily(this.daily);
        return weatherDataForecast;
    }
}

