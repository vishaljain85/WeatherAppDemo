package com.vg.repository;

import com.vg.repository.model.WeatherForecastStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherForecastRepository extends JpaRepository<WeatherForecastStore, String> {


    @Query(value = "select * FROM city_weather_forecast WHERE city = ?1 order by dt", nativeQuery = true)
    List<WeatherForecastStore> findAllByCity(String city);

}
