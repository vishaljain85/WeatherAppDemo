package com.vg.controller;

import com.vg.controller.builder.WeatherTheoryBuilder;
import com.vg.repository.UserPreferenceRepository;
import com.vg.repository.WeatherForecastRepository;
import com.vg.repository.model.WeatherForecastStore;
import com.vg.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WeatherControllerTest {

    @InjectMocks
    WeatherController weatherController;

    @Mock
    WeatherService weatherService;

    @Mock
    WeatherForecastRepository weatherForecastRepository;

    @Mock
    UserPreferenceRepository userPreferenceRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("When weather forecast of city is in the database, it does not invokes Open Weather Map API")
    public void getWeatherForecastFromDb() {
        String city = "Melbourne";
        String userId = "user1";

        WeatherTheoryBuilder theoryBuilder = new WeatherTheoryBuilder();

        List<WeatherForecastStore> storeList = new ArrayList<>();
        storeList.add(theoryBuilder.buildWeatherForecastStore());

        when(weatherForecastRepository.findAllByCity("Melbourne")).thenReturn(storeList);
        when(weatherService.getWeatherForecast(city)).thenReturn(theoryBuilder.buildOpenWeatherForecast());
        when(userPreferenceRepository.findById("Tom")).thenReturn(null);

        ResponseEntity<?> response = weatherController.getWeatherForecast(city, userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(theoryBuilder.buildWeatherDataForecast(), response.getBody());
        verify(weatherService, times(0)).getWeatherForecast(city);
    }

    @Test
    @DisplayName("When weather forecast of city is not in the database, it invokes Open Weather Map API")
    public void getWeatherForecast_OpenWeatherMapApi() {
        String city = "Melbourne";
        String userId = "user1";

        WeatherTheoryBuilder theoryBuilder = new WeatherTheoryBuilder();

        when(weatherForecastRepository.findAllByCity("Melbourne")).thenReturn(null);
        when(weatherService.getWeatherForecast(city)).thenReturn(theoryBuilder.buildOpenWeatherForecast());
        when(userPreferenceRepository.findById("Tom")).thenReturn(null);

        ResponseEntity<?> response = weatherController.getWeatherForecast(city, userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(theoryBuilder.buildWeatherDataForecast(), response.getBody());
        verify(weatherService, times(1)).getWeatherForecast(city);
    }


}
