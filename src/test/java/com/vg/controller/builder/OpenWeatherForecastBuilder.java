package com.vg.controller.builder;

import com.github.javafaker.Faker;
import com.vg.service.model.OpenWeatherForecast;
import com.vg.service.model.OpenWeatherForecastContent;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OpenWeatherForecastBuilder {
    private int cnt;
    private List<OpenWeatherForecastContent> list;
    private static final Faker faker = new Faker();

    public OpenWeatherForecastBuilder() {
        this.cnt = faker.number().numberBetween(1, 10);
        this.list = IntStream.range(0, this.cnt)
                .mapToObj(i -> new OpenWeatherForecastContentBuilder().build())
                .collect(Collectors.toList());
    }

    public OpenWeatherForecastBuilder withCnt(int cnt) {
        this.cnt = cnt;
        return this;
    }

    public OpenWeatherForecastBuilder withList(List<OpenWeatherForecastContent> list) {
        this.list = list;
        return this;
    }

    public OpenWeatherForecast build() {
        OpenWeatherForecast openWeatherForecast = new OpenWeatherForecast();
        openWeatherForecast.setCnt(this.cnt);
        openWeatherForecast.setList(this.list);
        return openWeatherForecast;
    }
}

