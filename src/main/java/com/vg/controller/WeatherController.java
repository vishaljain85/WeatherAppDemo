package com.vg.controller;

import com.vg.mapper.WeatherMapper;
import com.vg.model.UserPreference;
import com.vg.model.WeatherDataForecast;
import com.vg.repository.UserPreferenceRepository;
import com.vg.repository.WeatherForecastRepository;
import com.vg.repository.model.UserPreferenceStore;
import com.vg.repository.model.WeatherForecastStore;
import com.vg.service.WeatherService;
import com.vg.service.model.OpenWeatherForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class WeatherController {

    @Autowired
    private final WeatherService weatherService;

    @Autowired
    private final UserPreferenceRepository userPreferenceRepository;

    @Autowired
    private final WeatherForecastRepository weatherForecastRepository;

    public WeatherController(
            WeatherService weatherService, UserPreferenceRepository userPreferenceRepository,
            WeatherForecastRepository weatherForecastRepository) {
        this.weatherService = weatherService;
        this.userPreferenceRepository = userPreferenceRepository;
        this.weatherForecastRepository = weatherForecastRepository;
    }

    @GetMapping("/weather/forecast")
    public ResponseEntity<?> getWeatherForecast(
            @RequestParam("city") String city, @RequestParam("userId") String userId
    ) {
        WeatherDataForecast mapForecast = null;
        WeatherMapper weatherMapper = new WeatherMapper();
        List<WeatherForecastStore> weatherForecastStoreList = this.weatherForecastRepository.findAllByCity(city);
        if(weatherForecastStoreList != null && !weatherForecastStoreList.isEmpty()) {
            mapForecast = weatherMapper.mapToWeatherForecastResponse(weatherForecastStoreList);
        } else {
            OpenWeatherForecast forecast = this.weatherService.getWeatherForecast(city);
            List<WeatherForecastStore> mappedList = weatherMapper.mapToWeatherForecastStore(city, forecast);
            this.weatherForecastRepository.saveAll(mappedList);
            mapForecast = weatherMapper.mapToWeatherForecastResponse(mappedList);
        }
        UserPreference userPreference = this.retrieveUserPreference(userId);
        if(userPreference != null) {
            mapForecast.setMaxTempAlert(userPreference.getMaxTempAlert());
            mapForecast.getDaily().forEach((daily) -> {
                        daily.getHourly().forEach((houly) -> {
                            if(houly.getMax_temperature() >= userPreference.getMaxTempAlert()) {
                                daily.setAlertUser(true);
                            }
                        });
                    }
            );
        }
        return ResponseEntity.ok(mapForecast);
    }


    @PostMapping("/user/preference")
    public ResponseEntity<?> saveUserPreference(
            @RequestBody UserPreference userPreference
            ) {
        WeatherMapper weatherMapper = new WeatherMapper();
        UserPreferenceStore userPreferenceStore = weatherMapper.mapToUserPreferenceStore(userPreference);
        this.userPreferenceRepository.save(userPreferenceStore);

        return ResponseEntity.ok(userPreference);
    }

    @GetMapping("/user/preference")
    public ResponseEntity<?> getUserPreference(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(this.retrieveUserPreference(userId));
    }

    private UserPreference retrieveUserPreference(String userId){
        Optional<UserPreferenceStore> userPreferenceStore = this.userPreferenceRepository.findById(userId);
        UserPreference userPreference = null;
        WeatherMapper weatherMapper = new WeatherMapper();
        if(userPreferenceStore.isPresent()) {
            userPreference = weatherMapper.mapToUserPreference(userPreferenceStore.get());
        }
        return userPreference;
    }
}



