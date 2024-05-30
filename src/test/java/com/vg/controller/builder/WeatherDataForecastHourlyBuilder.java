package com.vg.controller.builder;

import com.github.javafaker.Faker;
import com.vg.model.WeatherDataForecastHourly;

public class WeatherDataForecastHourlyBuilder {
    private String description;
    private String hour;
    private double temperature;
    private double min_temperature;
    private double max_temperature;
    private static final Faker faker = new Faker();

    public WeatherDataForecastHourlyBuilder() {
        this.description = faker.weather().description();
        this.hour = String.valueOf(faker.number().numberBetween(0,24));
        this.temperature = faker.number().randomDouble(2, 0, 50);
        this.min_temperature = faker.number().randomDouble(2, 0, 50);
        this.max_temperature = faker.number().randomDouble(2, 0, 50);
    }

    public WeatherDataForecastHourlyBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public WeatherDataForecastHourlyBuilder withHour(String hour) {
        this.hour = hour;
        return this;
    }

    public WeatherDataForecastHourlyBuilder withTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    public WeatherDataForecastHourlyBuilder withMinTemperature(double min_temperature) {
        this.min_temperature = min_temperature;
        return this;
    }

    public WeatherDataForecastHourlyBuilder withMaxTemperature(double max_temperature) {
        this.max_temperature = max_temperature;
        return this;
    }

    public WeatherDataForecastHourly build() {
        WeatherDataForecastHourly weatherDataForecastHourly = new WeatherDataForecastHourly();
        weatherDataForecastHourly.setDescription(this.description);
        weatherDataForecastHourly.setHour(this.hour);
        weatherDataForecastHourly.setTemperature(this.temperature);
        weatherDataForecastHourly.setMin_temperature(this.min_temperature);
        weatherDataForecastHourly.setMax_temperature(this.max_temperature);
        return weatherDataForecastHourly;
    }
}

