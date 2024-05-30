package com.vg.controller.builder;

import com.github.javafaker.Faker;
import com.vg.repository.model.WeatherForecastStore;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WeatherForecastStoreBuilder {
    private long id;
    private String city;
    private String description;
    private long dt;
    private double temp;
    private double min_temp;
    private double max_temp;
    private Date modified_date;
    private static final Faker faker = new Faker();

    public WeatherForecastStoreBuilder() {
        this.id = faker.number().randomNumber();
        this.city = faker.address().city();
        this.description = faker.weather().description();
        this.dt = faker.date().future(5, TimeUnit.DAYS).getTime();
        this.temp = faker.number().randomDouble(2, -50, 50);
        this.min_temp = faker.number().randomDouble(2, 0, 50);
        this.max_temp = faker.number().randomDouble(2, 0, 50);
        this.modified_date = faker.date().past(30, TimeUnit.DAYS);
    }

    public WeatherForecastStoreBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public WeatherForecastStoreBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public WeatherForecastStoreBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public WeatherForecastStoreBuilder withDt(long dt) {
        this.dt = dt;
        return this;
    }

    public WeatherForecastStoreBuilder withTemp(double temp) {
        this.temp = temp;
        return this;
    }

    public WeatherForecastStoreBuilder withMinTemp(double min_temp) {
        this.min_temp = min_temp;
        return this;
    }

    public WeatherForecastStoreBuilder withMaxTemp(double max_temp) {
        this.max_temp = max_temp;
        return this;
    }

    public WeatherForecastStoreBuilder withModifiedDate(Date modified_date) {
        this.modified_date = modified_date;
        return this;
    }

    public WeatherForecastStore build() {
        WeatherForecastStore weatherForecastStore = new WeatherForecastStore();
        weatherForecastStore.setId(this.id);
        weatherForecastStore.setCity(this.city);
        weatherForecastStore.setDescription(this.description);
        weatherForecastStore.setDt(this.dt);
        weatherForecastStore.setTemp(this.temp);
        weatherForecastStore.setMin_temp(this.min_temp);
        weatherForecastStore.setMax_temp(this.max_temp);
        weatherForecastStore.setModified_date(this.modified_date);
        return weatherForecastStore;
    }
}

