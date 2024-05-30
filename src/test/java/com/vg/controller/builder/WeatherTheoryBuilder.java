package com.vg.controller.builder;

import com.github.javafaker.Faker;
import com.vg.model.WeatherDataForecast;
import com.vg.model.WeatherDataForecastDaily;
import com.vg.model.WeatherDataForecastHourly;
import com.vg.repository.model.WeatherForecastStore;
import com.vg.service.model.OpenWeatherDescription;
import com.vg.service.model.OpenWeatherForecast;
import com.vg.service.model.OpenWeatherForecastContent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class WeatherTheoryBuilder {
    private WeatherDataForecastBuilder forecastBuilder;

    private WeatherForecastStoreBuilder forecastStoreBuilder;

    private OpenWeatherForecastBuilder openWeatherForecastBuilder;

    private static final Faker faker = new Faker();

    public WeatherTheoryBuilder(){
        double temp = faker.number().randomDouble(2, 0, 50);
        double temp_min = faker.number().randomDouble(2, 0, 50);
        double temp_max = faker.number().randomDouble(2, 0, 50);
        String description = faker.weather().description();
        long dt = faker.date().future(10, TimeUnit.DAYS).getTime();
        String hr = getHour(dt);
        String strDt = getDate(dt);

        OpenWeatherDescription openWeatherDescription = new OpenWeatherDescriptionBuilder()
                .withDescription(description)
                .build();
        List<OpenWeatherDescription> openWeatherDescriptionList = new ArrayList();
        openWeatherDescriptionList.add(openWeatherDescription);

        OpenWeatherForecastContent openWeatherForecastContent = new OpenWeatherForecastContentBuilder()
                .withWeather(openWeatherDescriptionList)
                .withDt(dt)
                .withMain(
                        new OpenWeatherForecastMainBuilder()
                                .withTemp(temp)
                                .withTempMax(temp_max)
                                .withTempMin(temp_min)
                                .build()
                ).build();



        List<OpenWeatherForecastContent> openWeatherForecastContentList = new ArrayList<>();
        openWeatherForecastContentList.add(openWeatherForecastContent);

        WeatherDataForecastHourly hourly = new WeatherDataForecastHourlyBuilder()
                .withDescription(description)
                .withHour(hr)
                .withMaxTemperature(temp_max)
                .withMinTemperature(temp_min)
                .withTemperature(temp)
                .build();
        List<WeatherDataForecastHourly> hourlyList = new ArrayList<>();
        hourlyList.add(hourly);

        WeatherDataForecastDaily daily = new WeatherDataForecastDailyBuilder()
                .withDt(strDt)
                .withAlertUser(false)
                .withHourly(hourlyList)
                .build();
        List<WeatherDataForecastDaily> weatherDataForecastDailyList = new ArrayList<>();
        weatherDataForecastDailyList.add(daily);


        this.forecastBuilder = new WeatherDataForecastBuilder()
                .withMaxTempAlert(0)
                .withDaily(weatherDataForecastDailyList);
        this.forecastStoreBuilder = new WeatherForecastStoreBuilder()
                .withId(123)
                .withCity("Melbourne")
                .withDescription(description)
                .withDt(dt)
                .withTemp(temp)
                .withMinTemp(temp_min)
                .withMaxTemp(temp_max)
                .withModifiedDate(new Date(System.currentTimeMillis()));
        this.openWeatherForecastBuilder = new OpenWeatherForecastBuilder()
                .withList(openWeatherForecastContentList);
    }

    public WeatherDataForecast buildWeatherDataForecast() {
        return this.forecastBuilder.build();
    }

    public WeatherForecastStore buildWeatherForecastStore() {
        return this.forecastStoreBuilder.build();
    }

    public OpenWeatherForecast buildOpenWeatherForecast() {
        return this.openWeatherForecastBuilder.build();
    }

    private static String getDate(long dt){
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        sdfDate.setTimeZone(TimeZone.getDefault());
        return sdfDate.format(date);
    }

    private static String getHour(long dt){
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdfHour = new SimpleDateFormat("HH");
        sdfHour.setTimeZone(TimeZone.getDefault());
        return sdfHour.format(date);
    }
}
