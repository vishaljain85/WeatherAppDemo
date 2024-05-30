package com.vg.controller.builder;

import com.github.javafaker.Faker;
import com.vg.service.model.OpenWeatherForecastMain;

public class OpenWeatherForecastMainBuilder {
    private double temp;
    private double temp_min;
    private double temp_max;
    private static final Faker faker = new Faker();

    public OpenWeatherForecastMainBuilder() {
        this.temp = faker.number().randomDouble(2, 0, 50);
        this.temp_min = faker.number().randomDouble(2, 0, 50);
        this.temp_max = faker.number().randomDouble(2, 0, 50);
    }

    public OpenWeatherForecastMainBuilder withTemp(double temp) {
        this.temp = temp;
        return this;
    }

    public OpenWeatherForecastMainBuilder withTempMin(double temp_min) {
        this.temp_min = temp_min;
        return this;
    }

    public OpenWeatherForecastMainBuilder withTempMax(double temp_max) {
        this.temp_max = temp_max;
        return this;
    }

    public OpenWeatherForecastMain build() {
        OpenWeatherForecastMain openWeatherForecastMain = new OpenWeatherForecastMain();
        openWeatherForecastMain.setTemp(this.temp);
        openWeatherForecastMain.setTemp_min(this.temp_min);
        openWeatherForecastMain.setTemp_max(this.temp_max);
        return openWeatherForecastMain;
    }
}

