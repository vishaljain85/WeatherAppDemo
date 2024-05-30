package com.vg.mapper;

import com.vg.model.UserPreference;
import com.vg.model.WeatherDataForecast;
import com.vg.model.WeatherDataForecastDaily;
import com.vg.model.WeatherDataForecastHourly;
import com.vg.repository.model.UserPreferenceStore;
import com.vg.repository.model.WeatherForecastStore;
import com.vg.service.model.OpenWeatherForecast;
import com.vg.service.model.OpenWeatherForecastContent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class WeatherMapper {
    public List<WeatherForecastStore> mapToWeatherForecastStore(String city, OpenWeatherForecast openWeatherForecast){
        List<WeatherForecastStore> weatherForecastStoreList = new ArrayList<>();

        for(OpenWeatherForecastContent content : openWeatherForecast.getList()) {
            WeatherForecastStore weatherForecastStore = new WeatherForecastStore();
            weatherForecastStore.setCity(city);
            weatherForecastStore.setDescription(content.getWeather().get(0).getDescription());
            weatherForecastStore.setDt(content.getDt());
            weatherForecastStore.setTemp(content.getMain().getTemp());
            weatherForecastStore.setMax_temp(content.getMain().getTemp_max());
            weatherForecastStore.setMin_temp(content.getMain().getTemp_min());
            weatherForecastStore.setModified_date(new Date(System.currentTimeMillis()));
            weatherForecastStoreList.add(weatherForecastStore);
        }

        return weatherForecastStoreList;
    }

    public WeatherDataForecast mapToWeatherForecastResponse(List<WeatherForecastStore> weatherForecastStoreList){
        WeatherDataForecast dataForecast = new WeatherDataForecast();
        List<WeatherDataForecastDaily> dailyList = new ArrayList<>();
        dataForecast.setDaily(dailyList);
        WeatherDataForecastDaily daily = null;

        for(WeatherForecastStore data: weatherForecastStoreList){
            String currentDate = getDate(data.getDt());
            if (daily == null || !daily.getDt().equals(currentDate)) {
                daily = new WeatherDataForecastDaily();
                daily.setDt(currentDate);
                daily.setHourly(new ArrayList<>());
                dataForecast.getDaily().add(daily);
            }
            WeatherDataForecastHourly hourly = new WeatherDataForecastHourly();
            hourly.setDescription(data.getDescription());
            hourly.setHour(getHour(data.getDt()));
            hourly.setTemperature(data.getTemp());
            hourly.setMax_temperature(data.getMax_temp());
            hourly.setMin_temperature(data.getMin_temp());
            daily.getHourly().add(hourly);
        }
        return dataForecast;
    }

    public UserPreferenceStore mapToUserPreferenceStore(UserPreference userPreference) {
        UserPreferenceStore userPreferenceStore = new UserPreferenceStore();
        userPreferenceStore.setUser_id(userPreference.getUserId());
        userPreferenceStore.setMax_temp_alert(userPreference.getMaxTempAlert());

        return userPreferenceStore;
    }

    public UserPreference mapToUserPreference(UserPreferenceStore userPreferenceStore) {
        UserPreference userPreference = null;
        if(userPreferenceStore != null){
            userPreference = new UserPreference();
            userPreference.setUserId(userPreferenceStore.getUser_id());
            userPreference.setMaxTempAlert(userPreferenceStore.getMax_temp_alert());
        }
        return userPreference;
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
